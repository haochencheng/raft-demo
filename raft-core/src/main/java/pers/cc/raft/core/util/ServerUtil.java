package pers.cc.raft.core.util;

import lombok.Data;
import pers.cc.raft.core.peer.Peer;
import pers.cc.raft.core.peer.Server;

import java.util.List;

/**
 * @description:
 * @author: haochencheng
 * @create: 2020-01-16 14:39
 **/
@Data
public class ServerUtil {

    private ServerUtil(){

    }

    public static boolean isLocalServer(String serverId, List<Peer> peerList){
        return peerList.stream().filter(peer -> peer.getServer().getServerId().equalsIgnoreCase(serverId)).findAny().isPresent();
    }

}
