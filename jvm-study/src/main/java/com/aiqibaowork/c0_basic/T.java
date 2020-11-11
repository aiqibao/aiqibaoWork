package com.aiqibaowork.c0_basic;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:aiqibao
 * @Date:2020/11/6 15:16
 * Best wish!
 */
public class T {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>() ;
        Runtime r = Runtime.getRuntime() ;
        long l1 = r.freeMemory() ;
        System.out.println("r.totalMemory():"+r.totalMemory()/1024/1024);
        System.out.println(l1/1024/1024);
        for (int i = 0; i <100_0000 ; i++) {
            list.add(new Object()) ;
        }
        long l2 = r.freeMemory() ;
        System.out.println((l1 - l2)/100_0000);
    }
}
