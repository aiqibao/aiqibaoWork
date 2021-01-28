import java.util.concurrent.TimeUnit;

/**
 * @Author:aiqibao
 * @Date:2021/1/15 10:16
 * Best wish!
 */
public class T01_WhatIsThread {
    public static class MyThread extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("MyThread");
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        for (int i = 0; i <10 ; i++) {
            System.out.println("main");
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
