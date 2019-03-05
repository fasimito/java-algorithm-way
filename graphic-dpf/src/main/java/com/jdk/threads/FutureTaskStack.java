package com.jdk.threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskStack {

    public static void main(String[] args){

        FutureTask futureTask = new FutureTask(()->{
            System.out.println(Thread.currentThread().getName()+"-->我是通过实现Callable接口通过FutureTask");
            return new String("abc");
        });
        Thread thread = new Thread(futureTask);
        System.out.println(Thread.currentThread().getName());
        thread.start();

        try {
            String str = (String) futureTask.get();
            System.out.println("Returned value: "+str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
