package com.aiqibao.testreact;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:aiqibao
 * @Date:2021/1/28 9:49
 * Best wish!
 */
public class SelectorThreadGroup {

    ServerSocketChannel serverSocketChanne ;

    SelectorThread[] sts ;

    SelectorThreadGroup selectorThreadGroup = this ;

    AtomicInteger ait = new AtomicInteger(0) ;

    public SelectorThreadGroup(int num){
        sts = new SelectorThread[num] ;
        for (int i = 0; i < num; i++) {
            sts[i] = new SelectorThread(this);
            new Thread(sts[i]).start();
        }
    }

    public void setWorker(SelectorThreadGroup stg){
        this.selectorThreadGroup = stg ;
    }

    public void bind(int port) {
        try {
            serverSocketChanne = ServerSocketChannel.open() ;
            serverSocketChanne.configureBlocking(false) ;
            serverSocketChanne.bind(new InetSocketAddress(port)) ;
            nextSelector(serverSocketChanne) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nextSelector(Channel c) {
        if (c instanceof ServerSocketChannel){
            SelectorThread st = next() ;
            st.lbg.add(c) ;
            st.setWork(selectorThreadGroup) ;
            st.selector.wakeup() ;
        }else if (c instanceof SocketChannel){
            SelectorThread st = next3() ;
            st.lbg.add(c) ;
            st.selector.wakeup() ;
        }

    }

    private SelectorThread next3() {
        int index = ait.incrementAndGet() % selectorThreadGroup.sts.length;
        return selectorThreadGroup.sts[index] ;
    }

    private SelectorThread next() {
        int index = ait.incrementAndGet()%sts.length ;
        return sts[index] ;
    }
}
