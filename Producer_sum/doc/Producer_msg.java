package producer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    private int num=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    public void increment(){
        lock.lock();
        try {
            while (num!=0){
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            num++;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void decrement(){
        lock.lock();
        try {
            while (num==0){
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            num--;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public  class Producer_msg {
    public static void main(String[] args) {
        ShareData shareData=new ShareData();
        for (int i = 1; i <=5 ; i++) {
            new Thread(()->{
                shareData.increment();
            },"t1").start();
        }
        for (int i = 1; i <=5 ; i++) {
            new Thread(()->{
                shareData.decrement();
            },"t2").start();
        }
        for (int i = 1; i <=5 ; i++) {
            new Thread(()->{
                shareData.increment();
            },"t3").start();
        }
        for (int i = 1; i <=5 ; i++) {
            new Thread(()->{
                shareData.decrement();
            },"t4").start();
        }
    }
}

