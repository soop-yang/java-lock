package com.tedu.eurekaserver;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class TestJ implements Runnable{
    public synchronized void sendmsg(){
        System.out.println(Thread.currentThread().getName()+" send msg");
        sendemail();
    }
    public synchronized void sendemail(){
        System.out.println(Thread.currentThread().getName()+" send email");
    }
    Lock lock=new ReentrantLock();
    @Override
    public void run() {
        get();

    }
    private void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" invoke get()");
            set();
        }finally {
            lock.unlock();
        }
    }
    private void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" invoke set()");
        }finally {
            lock.unlock();
        }
    }
}
class test01{
    public static void main(String[] args) {
        TestJ testJ=new TestJ();
        new Thread(()->{
            testJ.sendmsg();
        },"t1").start();
        new Thread(()->{
            testJ.sendmsg();
        },"t2").start();
        new Thread(()->{
            testJ.run();
        },"t3").start();
        new Thread(()->{
            testJ.run();
        },"t4").start();
    }
}
