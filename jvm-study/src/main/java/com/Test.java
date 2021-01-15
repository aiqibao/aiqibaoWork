package com;

/**
 * @Author:aiqibao
 * @Date:2020/12/31 10:47
 * Best wish!
 */
public class Test {
    static final  Integer counts=0  ;
    static volatile Integer count = 0 ;
    public static void incr(){
        synchronized (counts){
            count++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            new Thread(()->Test.incr()).start();
        }
        Thread.sleep(5000);
        System.out.println("cout"+count);
    }
}
