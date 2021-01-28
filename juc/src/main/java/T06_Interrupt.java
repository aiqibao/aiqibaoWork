import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @Author:aiqibao
 * @Date:2021/1/15 16:01
 * Best wish!
 */
public class T06_Interrupt {
    private static int i ;
    public static void main(String[] args) throws InterruptedException {
        //interrupt01();
        interrupt02();
    }

    static void interrupt01(){
        Thread thread = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
                i++ ;
            }
        });
        thread.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println(i);
    }

    static void interrupt02() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                for (int j = 0; j < 10; j++) {
                    System.out.println("i:" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }
}
