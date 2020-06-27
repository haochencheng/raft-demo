package pers.cc.raft.core;

import lombok.Data;

/**
 * @description:
 * @author: haochencheng
 * @create: 2020-01-15 16:41
 **/
@Data
public class RaftConfig {

    /**
     * A follower would become a candidate if it doesn't receive any message
     * from the leader in electionTimeoutMs milliseconds
      */
    private int electionTimeoutMilliseconds = 3000;

    /**
     * A leader sends RPCs at least this often, even if there is no data to send
     */
    private int heartbeatPeriodMilliseconds = 500;

    /**
     * 与其他节点进行同步、选主等操作的线程池大小
     */
    private int raftCoreThreadNum = 20;





}
