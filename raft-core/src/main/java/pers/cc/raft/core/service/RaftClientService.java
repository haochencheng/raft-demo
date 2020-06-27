package pers.cc.raft.core.service;

/**
 * @description:
 * @author: haochencheng
 * @create: 2020-01-15 15:51
 **/
public interface RaftClientService {


    /**
     * 一定时间没有收到leader的心跳/(续租)候选人发起投票
     */
    void requestVote();

    /**
     * 附加日志
     */
    void appendEntries();

    /**
     *
     */
    void installSnapshot();



}
