package pers.cc.raft.core;

/**
 * @description:
 * @author: haochencheng
 * @create: 2020-01-17 11:49
 **/
public class RaftContextHolder {

    private static RaftContext raftContext;

    private RaftContextHolder(){

    }

    public static void raftContext(RaftContext raftContext){
        RaftContextHolder.raftContext=raftContext;
    }

    public static RaftContext getRaftContext(){
        return RaftContextHolder.raftContext;
    }
}
