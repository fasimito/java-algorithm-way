package com.algorithm.jdk.spec.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Jate.ma
 */
public class MyServiceReentrantLock {
    private Lock lock = new ReentrantLock();
    private Condition condition=lock.newCondition();
    public void testReentrant(){
        try {
            lock.lock();
            System.out.println("start wait......");
            condition.await();
            for(int i=0;i<5;i++){
                System.out.println("ThreadName=" + Thread.currentThread().getName()
                        + (" " + (i + 1)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        final MyServiceReentrantLock myService = new MyServiceReentrantLock();
        for(int i=0;i<5;i++){
            new Thread(()->{
                myService.testReentrant();
            }).start();
        }
    }
}
