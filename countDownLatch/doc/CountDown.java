package com.tedu.eurekaserver;

import com.tedu.eurekaserver.enume.CountryEnum;

import java.util.concurrent.CountDownLatch;

public class CountDown {
    public static void main(String[] args) {
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for (int i = 1; i <=6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国，被灭");
                countDownLatch.countDown();
            },CountryEnum.setMsg(i).getCountry()).start();
        }
        try { countDownLatch.await(); } catch (InterruptedException e) { e.printStackTrace();}
        System.out.println(Thread.currentThread().getName()+"\t 秦国统一华夏");
    }
}
