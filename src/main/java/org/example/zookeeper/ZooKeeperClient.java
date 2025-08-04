package org.example.zookeeper;

import org.apache.zookeeper.ClientCnxn;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.client.HostProvider;
import org.apache.zookeeper.client.StaticHostProvider;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author qiaobao
 * @since 2025/7/30
 */
public class ZooKeeperClient {

//    private final ClientCnxn cnxn; // 网络连接管理器

    public ZooKeeperClient(String connectString, int sessionTimeout, Watcher watcher) {
        // 解析服务端地址（如 "localhost:2181"）
//        HostProvider hostProvider = new StaticHostProvider();
//        // 初始化网络连接（基于 NIO 的 ClientCnxn）
//        this.cnxn = new ClientCnxn("root",hostProvider, sessionTimeout, watcher, true);
//        // 启动连接线程
//        cnxn.start();
    }
}
