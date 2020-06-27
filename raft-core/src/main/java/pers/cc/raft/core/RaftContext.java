package pers.cc.raft.core;

import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import pers.cc.raft.core.peer.Endpoint;
import pers.cc.raft.core.peer.Peer;
import pers.cc.raft.core.peer.Server;
import pers.cc.raft.core.util.ServerUtil;
import pers.cc.raft.core.util.StringUtil;
import pers.raft.proto.RaftProto;
import pers.raft.proto.RaftServiceGrpc;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: haochencheng
 * @create: 2020-01-15 16:03
 **/
@Data
@Slf4j
public class RaftContext {

    public static final int HALF = 2;
    private Node node;

    private RaftConfig raftConfig;

    /**
     * 选举定时器
     */
    private ScheduledFuture electionScheduledFuture;

    /**
     * 心跳定时器
     */
    private ScheduledFuture heartbeatScheduledFuture;

    /**
     * 核心线程池
     */
    private ExecutorService executorService;

    private Lock lock = new ReentrantLock();

    private ConcurrentMap<String, Peer> peerMap;

    private String localServerId;

    private List<Boolean> votedForList = new CopyOnWriteArrayList<>();

    private Map<String, RaftServiceGrpc.RaftServiceStub> raftServiceStubMap = new HashMap<>(8);

    private ScheduledExecutorService scheduledExecutorService;

    public RaftContext(String localServerId, ConcurrentMap<String, Peer> peerMap) {
        this.localServerId = localServerId;
        this.peerMap = peerMap;
        this.raftConfig = new RaftConfig();
        this.node = new Node();
        scheduledExecutorService = Executors.newScheduledThreadPool(2, r -> {
            Thread thread = new Thread(r);
            thread.setName("electionThread");
            return thread;
        });
        executorService = new ThreadPoolExecutor(
                raftConfig.getRaftCoreThreadNum(),
                raftConfig.getRaftCoreThreadNum(),
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue());
    }

    public void start() {
        // 重置选举超时时间
        resetElectionTimer();
    }

    /**
     * 客户端发起vote请求
     */
    private void startVote() {
        node.changeNodeState(NodeState.CANDIDATE);
        node.setCurrentTerm(node.getCurrentTerm() + 1);
        votedForList = new CopyOnWriteArrayList();
        votedForList.add(true);
        Collection<Peer> peerList = peerMap.values();
        peerList.forEach(peer -> {
            Server server = peer.getServer();
            String serverId = server.getServerId();
            if (serverId.equalsIgnoreCase(this.localServerId)) {
                return;
            }
            executorService.submit(() -> {
                // 获取代理
                RaftServiceGrpc.RaftServiceStub raftServiceStub = peer.getRaftServiceStub();
                RaftProto.VoteRequest voteRequest = RaftProto.VoteRequest.newBuilder()
                        .setCandidateId(localServerId)
                        .setTerm(node.getCurrentTerm())
                        .build();
                raftServiceStub.requestVote(voteRequest, getRequestVoteCallBack());
            });
        });
        resetElectionTimer();
    }

    /**
     * 处理投票结果
     * 候选人（5.2 节）：
     * <p>
     * 在转变成候选人后就立即开始选举过程
     * 自增当前的任期号（currentTerm）
     * 给自己投票
     * 重置选举超时计时器
     * 发送请求投票的 RPC 给其他所有服务器
     * 如果接收到大多数服务器的选票，那么就变成领导人
     * 如果接收到来自新的领导人的附加日志 RPC，转变成跟随者
     * 如果选举过程超时，再次发起一轮选举
     *
     * @return
     */
    private StreamObserver<RaftProto.VoteResponse> getRequestVoteCallBack() {
        return new StreamObserver<RaftProto.VoteResponse>() {
            @Override
            public void onNext(RaftProto.VoteResponse voteResponse) {
                if (!node.getNodeState().equals(NodeState.CANDIDATE)) {
                    return;
                }
                if (voteResponse.getTerm() > node.getCurrentTerm()) {
                    stepDown(voteResponse.getTerm());
                    return;
                }
                log.debug("接收到 {} 投票结果", voteResponse.getVoteGranted());
                votedForList.add(voteResponse.getVoteGranted());
                if (votedForList.stream().filter(vr -> vr.booleanValue()).count() >= peerMap.size() / HALF) {
                    //成为leader
                    stepUp();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("请求投票失败:{}", throwable);
            }

            @Override
            public void onCompleted() {
                log.info("onCompleted===");
            }
        };
    }


    /**
     * 选举通过，变成leader
     * 领导人：
     * <p>
     * 一旦成为领导人：发送空的附加日志 RPC（心跳）给其他所有的服务器；在一定的空余时间之后不停的重复发送，以阻止跟随者超时（5.2 节）
     * 如果接收到来自客户端的请求：附加条目到本地日志中，在条目被应用到状态机后响应客户端（5.3 节）
     * 如果对于一个跟随者，最后日志条目的索引值大于等于 nextIndex，那么：发送从 nextIndex 开始的所有日志条目：
     * 如果成功：更新相应跟随者的 nextIndex 和 matchIndex
     * 如果因为日志不一致而失败，减少 nextIndex 重试
     * 如果存在一个满足N > commitIndex的 N，并且大多数的matchIndex[i] ≥ N成立，并且log[N].term == currentTerm成立，那么令 commitIndex 等于这个 N （5.3 和 5.4 节）
     */
    public void stepUp() {
        node.setNodeState(NodeState.LEADER);
        node.setVotedFor(StringUtil.EMPTY);
        startNewHeartbeat();
    }

    /**
     * leader开始心跳检测
     */
    private void startNewHeartbeat() {
        log.debug("start new heartbeat, peers={}", peerMap);
        Collection<Peer> peerList = peerMap.values();
        peerList.forEach(peer -> {
            Server server = peer.getServer();
            executorService.submit(() -> appendEntries(peer));
        });
        resetHeartbeatTimer();
    }

    /**
     * heartbeat timer, append entries
     * in lock
     */
    private void resetHeartbeatTimer() {
        if (Objects.nonNull(heartbeatScheduledFuture) && !heartbeatScheduledFuture.isDone()) {
            heartbeatScheduledFuture.cancel(true);
        }
//        scheduledExecutorService.schedule()
    }

    private void appendEntries(Peer peer) {

    }

    /**
     * 当前任期小于投票返回任期，变成跟随者
     *
     * @param newTerm
     */
    public void stepDown(long newTerm) {
        node.setCurrentTerm(newTerm);
        node.setLeaderId(StringUtil.EMPTY);
        node.setVotedFor(StringUtil.EMPTY);
        node.setNodeState(NodeState.FOLLOWER);
        // stop heartbeat
        if (heartbeatScheduledFuture != null && !heartbeatScheduledFuture.isDone()) {
            heartbeatScheduledFuture.cancel(true);
        }
        resetElectionTimer();
    }

    /**
     * 选举定时器
     */
    private void resetElectionTimer() {
        if (electionScheduledFuture != null && !electionScheduledFuture.isDone()) {
            electionScheduledFuture.cancel(true);
        }
        //开启随机选举
        electionScheduledFuture = scheduledExecutorService.schedule(() -> startVote(), getElectionTimeoutMs(), TimeUnit.MILLISECONDS);
    }

    private int getElectionTimeoutMs() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int randomElectionTimeout = raftConfig.getHeartbeatPeriodMilliseconds()
                + random.nextInt(0, 2 * raftConfig.getHeartbeatPeriodMilliseconds());
        log.debug("new election time is after {} ms", randomElectionTimeout);
        return randomElectionTimeout;
    }


}
