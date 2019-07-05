import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Semaphores {
    public static void main(String[] args) {
        //模拟三个车位
        Semaphore semaphore=new Semaphore(3);
        //模拟6辆车抢3个车位
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t 3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }

            },String.valueOf(i)).start();

        }
    }

}
