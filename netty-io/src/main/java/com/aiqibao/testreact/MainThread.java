package com.aiqibao.testreact;

/**
 * @Author:aiqibao
 * @Date:2021/1/28 9:49
 * Best wish!
 */
public class MainThread {
    public static void main(String[] args) {
        SelectorThreadGroup boss = new SelectorThreadGroup(1);
        SelectorThreadGroup work = new SelectorThreadGroup(1);

        boss.setWorker(work);

        boss.bind(9090) ;
    }
}
