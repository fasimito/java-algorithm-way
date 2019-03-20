package com.algorithm.jdk.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class TestJVMCas3 {
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(10);  //if sleep(2000), there are wired results
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                for (int j = 0; j < 100; j++) {
                    count.incrementAndGet();
                }
            }).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count = "+count.get());
    }
}
