package com.aiqibao;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @Author:aiqibao
 * @Date:2021/1/21 17:33
 * Best wish!
 */
public class OSFileIo {
    @Test
    public void whatByteBuffer(){
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024) ;
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("mark:" + buffer.mark());

        buffer.put("12345".getBytes()) ;

        System.out.println("---------put:123");
        System.out.println("mark: " + buffer);

        buffer.flip() ;

        System.out.println("--------flip-------");
        System.out.println("mark: " + buffer);

        buffer.get();
        System.out.println("---------get---------");
        System.out.println("mark: " + buffer);

        buffer.compact();
        System.out.println("---------compact---------");
        System.out.println("mark: " + buffer);

        buffer.clear();
        System.out.println("---------clear---------");
        System.out.println("mark: " + buffer);

    }
}
