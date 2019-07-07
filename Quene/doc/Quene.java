import java.util.concurrent.*;

public class Quene {
    public static void main(String[] args) {
        SynchronousQueue synchronousQueue=new SynchronousQueue();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t queue a");
                synchronousQueue.put("a");
                System.out.println(Thread.currentThread().getName()+"\t queue b");
                synchronousQueue.put("b");
                System.out.println(Thread.currentThread().getName()+"\t queue c");
                synchronousQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(synchronousQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(synchronousQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(synchronousQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();

    }
}
