package com.javamastery.multithreading.Volatile;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerPractice {
    public static void main(String[] args)  throws InterruptedException{
        AtomicInteger orderCount = new AtomicInteger(0);

        Thread worker1 = new Thread(()->{
            System.out.println("Thread 1");
            for (int i = 0; i < 100_000; i++) {
                orderCount.incrementAndGet();
            }

        });

        Thread worker2 = new Thread(()->{
            System.out.println("Thread 2");

            for (int i = 0; i < 100_000; i++) {
                orderCount.incrementAndGet();
            }

        });

        worker1.start();
        worker2.start();

        worker1.join();
        worker2.join();

        System.out.println(orderCount);
    }
}
