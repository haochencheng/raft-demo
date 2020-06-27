package pers.cc.raft.core;

import lombok.Data;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: haochencheng
 * @create: 2020-01-10 15:34
 **/
@Data
public class Node {

    private Lock lock = new ReentrantLock();

    /**
     * 节点状态 默认FOLLOWER
     */
    private NodeState nodeState=NodeState.FOLLOWER;

    /**
     * 服务器最后一次知道的任期号（初始化为 0，持续递增）
     */
    private long currentTerm;

    /**
     * 在当前获得选票的候选人的 Id
     */
    private String votedFor;

    /**
     * 领导人的 Id，以便于跟随者重定向请求
     */
    private String leaderId;

    /**
     * 候选人的最后日志条目的索引值
     */
    private long lastLogIndex;


    public void changeNodeState(NodeState nodeState){
        lock.lock();
        this.nodeState=nodeState;
        lock.unlock();
    }


}
