package raft.server;


import io.grpc.Channel;
import io.grpc.Grpc;
import io.grpc.netty.shaded.io.grpc.netty.NegotiationType;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import pers.cc.raft.core.RaftContext;
import pers.cc.raft.core.constants.Constants;
import pers.cc.raft.core.peer.Endpoint;
import pers.cc.raft.core.peer.Peer;
import pers.cc.raft.core.peer.Server;
import pers.raft.proto.RaftServiceGrpc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @description:
 * @author: haochencheng
 * @create: 2020-01-15 11:03
 **/
@Slf4j
public class Bootstrap {

    public static final int START_UP_ARGS_NUM = 2;

    public static void main(String[] args) {
        if (args.length != START_UP_ARGS_NUM){
            log.error("Usage: ./run_server.sh CLUSTER CURRENT_NODE\n");
            System.exit(-1);
        }
        String[] clusterIpList = args[0].split(Constants.COMMA_DIVISION);
        ConcurrentMap<String, Peer>  peerMap=new ConcurrentHashMap<>(clusterIpList.length);
        for (int i = 0; i < clusterIpList.length; i++) {
            String[] ipPortSplit = clusterIpList[i].split(Constants.COLON_DIVISION);
            String host = ipPortSplit[0];
            String portStr = ipPortSplit[1];
            int port=0;
            try {
                port=Integer.parseInt(portStr);
            }catch (NumberFormatException e){
                e.printStackTrace();
                System.exit(1);
            }
            Endpoint endpoint=new Endpoint(host,port);
            String serverId = ipPortSplit[2];
            Server server=new Server(serverId,endpoint);

            Channel channel = NettyChannelBuilder.forAddress(host, port)
                    .negotiationType(NegotiationType.PLAINTEXT)
                    .build();
            RaftServiceGrpc.RaftServiceStub raftServiceFutureStub = RaftServiceGrpc.newStub(channel);
            Peer peer=new Peer(server,raftServiceFutureStub);
            peerMap.put(serverId,peer);
        }

        RaftContext raftContext=new RaftContext(args[1],peerMap);
        raftContext.start();

    }

}
