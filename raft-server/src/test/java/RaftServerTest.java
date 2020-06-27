import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.junit.Test;
import pers.cc.raft.core.service.impl.RaftServerServiceImpl;
import pers.raft.proto.RaftProto;
import pers.raft.proto.RaftServiceGrpc;

import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: haochencheng
 * @create: 2020-01-16 13:09
 **/
public class RaftServerTest {

    @Test
    public void testRequestVote(){
        Server server = ServerBuilder.forPort(8000).addService(new RaftServerServiceImpl()).build();
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8000)
                .usePlaintext()
                .build();
        RaftServiceGrpc.RaftServiceBlockingStub raftServiceBlockingStub = RaftServiceGrpc.newBlockingStub(channel);
        RaftProto.VoteResponse voteResponse = raftServiceBlockingStub.requestVote(RaftProto.VoteRequest.newBuilder().build());
        System.out.println(voteResponse.toString());
        server.shutdown();
    }

}
