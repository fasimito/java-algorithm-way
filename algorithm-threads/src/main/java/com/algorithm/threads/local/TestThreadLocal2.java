package com.algorithm.threads.local;

public class TestThreadLocal2 {

    /**
     * 重写initialValue方法就不需要先调用set方法，直接就可以使用get方法了。
     */
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue(){
            return Thread.currentThread().getId();
        }
    };
    ThreadLocal<String> stringLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };

    /**
     * 该set方法就可以不用了
     */
    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }
    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final TestThreadLocal2 test = new TestThreadLocal2();
        //test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());
        Thread thread1 = new Thread(()->{
            //test.set();
            System.out.println(test.getLong());
            System.out.println(test.getString());
        });
        thread1.start();
        thread1.join();
        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}
