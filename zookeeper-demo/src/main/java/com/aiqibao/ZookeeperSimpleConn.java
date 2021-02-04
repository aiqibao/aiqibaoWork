package com.aiqibao;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author:aiqibao
 * @Date:2021/2/4 11:32
 * Best wish!
 */
public class ZookeeperSimpleConn implements Watcher {
    public static CountDownLatch countDownLatch = new CountDownLatch(1) ;
    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("175.24.124.91:2181", 3000,new ZookeeperSimpleConn());
        System.out.println(zooKeeper.getState());
        try {
            countDownLatch.await();
        }catch (InterruptedException e){
            System.out.println("zookeeper session established");
        }

    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("receive watch event:"+event);
        if (Event.KeeperState.SyncConnected.equals(event.getState())){
            countDownLatch.countDown();
        }

    }
}
