/**
 * @Author:aiqibao
 * @Date:2021/1/15 11:12
 * Best wish!
 */
public class T04_ThreadState {
    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println(this.getState());
            for (int i = 0; i < 10; i++) {
                System.out.println("A"+i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MyThread myThread = new MyThread() ;
        System.out.println(myThread.getState());
        myThread.start();

        Thread.sleep(900);
        System.out.println(myThread.getState());
        myThread.wait();
        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(myThread.getState());
    }
}
