package pers.cc.raft.core.peer;

import lombok.Data;

/**
 * @description:
 * @author: haochencheng
 * @create: 2020-01-15 17:35
 **/
@Data
public class Server {

    private String serverId;

    private Endpoint endpoint;

    public Server(String serverId, Endpoint endpoint) {
        this.serverId = serverId;
        this.endpoint = endpoint;
    }
}
