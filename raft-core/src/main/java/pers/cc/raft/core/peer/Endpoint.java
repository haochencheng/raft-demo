package pers.cc.raft.core.peer;

import lombok.Data;

/**
 * @description:
 * @author: haochencheng
 * @create: 2020-01-16 14:37
 **/
@Data
public class Endpoint {

    String host;

    private Integer port;

    public Endpoint(String host, Integer port) {
        this.host = host;
        this.port = port;
    }
}
