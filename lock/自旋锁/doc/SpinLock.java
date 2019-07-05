import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    AtomicReference<Thread> threadAtomicReference =new AtomicReference<>();
    public void myLock(){
        Thread currentThread=Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in");
        while(!threadAtomicReference.compareAndSet(null,currentThread)){
        }
    }
    public void myUnlock(){
        Thread currentThread=Thread.currentThread();
        threadAtomicReference.compareAndSet(currentThread,null);
        System.out.println(Thread.currentThread().getName()+"\t invote myUnlock");
    }
}
class TestSpinLock{
    public static void main(String[] args) {
        SpinLock spinLock=new SpinLock();
        new Thread(()->{
            spinLock.myLock();
            try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) { e.printStackTrace();}
            spinLock.myUnlock();
        },"t1").start();
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) { e.printStackTrace();}
        new Thread(()->{
            spinLock.myLock();
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) { e.printStackTrace();}
            spinLock.myUnlock();
        },"t2").start();
    }
}
