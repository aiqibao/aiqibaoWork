/**
 * @Author:aiqibao
 * @Date:2021/1/15 10:09
 * Best wish!
 */
public class T02_HowToCreateThread {
    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("Hello MyThread");
        }
    }
    static class MyRunable implements Runnable{
        @Override
        public void run() {
            System.out.println("Hello MyRunable");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRunable()).start();
        new Thread(()->{
            System.out.println("Hello Lamda");
        }).start();
    }
}
