import java.util.concurrent.TimeUnit;

/**
 * @Author:aiqibao
 * @Date:2021/1/15 15:16
 * Best wish!
 */
public class T05_ThreadState {
    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Time_wating").start();

        new Thread(()->{
            try {
                synchronized (T05_ThreadState.class){
                    T05_ThreadState.class.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"wating").start();
        new Thread(new Blocked(),"Block01").start();
        new Thread(new Blocked(),"Block02").start();
    }
    static class Blocked extends Thread{
        @Override
        public void run() {
            synchronized (Blocked.class){
                try {
                    TimeUnit.SECONDS.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
