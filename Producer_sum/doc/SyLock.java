import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    //1:A 2:B 3:C
    private int num=1;
    private Lock lock=new ReentrantLock();
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();
    public void print5(){
        lock.lock();
        try {
            while (num!=1){
                c1.await();
            }
            for (int i = 1; i <=5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            num=2;
            c2.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print10(){
        lock.lock();
        try {
            while (num!=2){
                c2.await();
            }
            for (int i = 1; i <=10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            num=3;
            c3.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print15(){
        lock.lock();
        try {
            while (num!=3){
                c3.await();
            }
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            num=1;
            c1.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


}
public class SyLock {
    public static void main(String[] args) {
        ShareData shareData=new ShareData();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                shareData.print5();
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                shareData.print10();
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                shareData.print15();
            }
        },"CC").start();




    }
}
