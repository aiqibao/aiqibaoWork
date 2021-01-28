package com.aiqibao.testreact;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author:aiqibao
 * @Date:2021/1/28 9:49
 * Best wish!
 */
public class SelectorThread implements Runnable{

    SelectorThreadGroup selectorThreadGroup ;
    
    Selector selector = null;

    LinkedBlockingQueue<Channel> lbg = new LinkedBlockingQueue() ;

    SelectorThread(SelectorThreadGroup stg){
        this.selectorThreadGroup = stg ;
        try {
            selector = Selector.open() ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        while (true){
            try {
//                System.out.println(Thread.currentThread().getName() +" :before -----"+selector.keys().size());
                int num = selector.select();
//                System.out.println(Thread.currentThread().getName() +" :after -----"+selector.keys().size());
                if (num > 0){
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while(iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isAcceptable()){
                            acceptHandler(key) ;
                        }else if (key.isReadable()){
                            readHandler(key) ;
                        }else if (key.isWritable()){
                            
                        }
                    }
                }

                if (!lbg.isEmpty()){
                    try {
                        Channel c = lbg.take();
                        if (c instanceof ServerSocketChannel){
                            ServerSocketChannel server = (ServerSocketChannel) c ;
                            server.register(selector,SelectionKey.OP_ACCEPT);
                            System.out.println(Thread.currentThread().getName()+":register listen");
                        }else if (c instanceof SocketChannel){
                            SocketChannel client = (SocketChannel) c;
                            ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
                            client.register(selector,SelectionKey.OP_READ,buffer) ;
                            System.out.println(Thread.currentThread().getName()+":register read");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void readHandler(SelectionKey key) {
        System.out.println(Thread.currentThread().getName()+":readHander");
        ByteBuffer buffer = (ByteBuffer)key.attachment();
        SocketChannel client = (SocketChannel)key.channel() ;
        //buffer.clear() ;
        while(true){
            try {
                int read = client.read(buffer);
                if (read>0){
                    buffer.flip() ;
                    while (buffer.hasRemaining()){
                        client.write(buffer) ;
                    }
                    buffer.clear() ;
                }else if (read==0){
                    break;
                }else if (read<0){
                    System.out.println("client:"+client.getRemoteAddress()+"closed...");
                    key.cancel();
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void acceptHandler(SelectionKey key) {
        System.out.println(Thread.currentThread().getName()+" : acceptHandler");
        ServerSocketChannel server = (ServerSocketChannel)key.channel();
        try {
            SocketChannel client = server.accept();
            client.configureBlocking(false) ;
            selectorThreadGroup.nextSelector(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setWork(SelectorThreadGroup selectorThreadGroup) {
        this.selectorThreadGroup = selectorThreadGroup ;
    }
}
