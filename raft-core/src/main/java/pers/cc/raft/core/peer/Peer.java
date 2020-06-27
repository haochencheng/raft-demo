package pers.cc.raft.core.peer;

import lombok.Data;
import pers.raft.proto.RaftServiceGrpc;

/**
 * @description:
 * @author: haochencheng
 * @create: 2020-01-17 14:47
 **/
@Data
public class Peer {

    /**
     *  需要发送给follower的下一个日志条目的索引值，只对leader有效
     */
    private long nextIndex;

    /**
     *  已复制日志的最高索引值
     */
    private long matchIndex;

    /**
     * 节点服务器
     */
    private Server server;

    private RaftServiceGrpc.RaftServiceStub raftServiceStub;

    public Peer(Server server,RaftServiceGrpc.RaftServiceStub raftServiceStub) {
        this.server = server;
        this.raftServiceStub = raftServiceStub;
    }
}
