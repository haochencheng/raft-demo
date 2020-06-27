package pers.cc.raft.core.service.impl;

import com.google.common.base.Strings;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import pers.cc.raft.core.Node;
import pers.cc.raft.core.RaftContext;
import pers.cc.raft.core.RaftContextHolder;
import pers.raft.proto.RaftProto;
import pers.raft.proto.RaftServiceGrpc;

/**
 * @description:
 * @author: haochencheng
 * @create: 2020-01-16 13:15
 **/
@Slf4j
public class RaftServerServiceImpl extends RaftServiceGrpc.RaftServiceImplBase {

    /**
     * 接收者实现：
     * <p>
     * 如果term < currentTerm返回 false （5.2 节）
     * 如果 votedFor 为空或者为 candidateId，并且候选人的日志至少和自己一样新，那么就投票给他（5.2 节，5.4 节）
     *
     * 跟随者（5.2 节）：
     *
     * 响应来自候选人和领导者的请求
     * 如果在超过选举超时时间的情况之前都没有收到领导人的心跳，或者是候选人请求投票的，就自己变成候选人
     * @param request
     * @param responseObserver
     */
    @Override
    public void requestVote(RaftProto.VoteRequest request, StreamObserver<RaftProto.VoteResponse> responseObserver) {
        log.debug("接收到投票请求:{}请求投票,任期号为：{},最后提交日志任期号为：{},最后提交日志id为：{}",request.getCandidateId(),request.getTerm(),request.getLastLogTerm(),request.getLastLogIndex());
        RaftContext raftContext = RaftContextHolder.getRaftContext();
        Node node = raftContext.getCurrentnNode();
        try {
            node.getLock().lock();
            RaftProto.VoteResponse.Builder voteResponseBuilder = RaftProto.VoteResponse.newBuilder();
            voteResponseBuilder.setTerm(node.getCurrentTerm());
            voteResponseBuilder.setVoteGranted(false);
            if (Strings.isNullOrEmpty(node.getVotedFor())|| node.getVotedFor().equalsIgnoreCase(request.getCandidateId())) {
                if (request.getLastLogIndex() >= node.getLastLogIndex() && request.getTerm()>=node.getCurrentTerm()) {
                    voteResponseBuilder = voteResponseBuilder
                            .setVoteGranted(true);
                    node.setVotedFor(request.getCandidateId());
                }
            }
            responseObserver.onNext(voteResponseBuilder.build());
            responseObserver.onCompleted();
        }finally {
            node.getLock().unlock();
        }
    }
}
