import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CycliBarriers {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7, new Runnable() {
            @Override
            public void run() {
                System.out.println("已收集7颗龙珠，召唤神龙");
            }
        });
        for (int i = 1; i <=7; i++) {
            final int temp=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 已收集到"+temp+"个星星龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
