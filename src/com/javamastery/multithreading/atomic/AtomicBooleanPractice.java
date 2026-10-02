package com.javamastery.multithreading.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanPractice {
    public static void main(String[] args) throws  InterruptedException {
        AtomicBoolean serviceStarted = new AtomicBoolean(false);

        Thread worker1 = new Thread(()->{
            if (serviceStarted.compareAndSet(false, true)) {
                System.out.println(
                        Thread.currentThread().getName() +
                                " started the service"
                );
            } else {
                System.out.println(
                        Thread.currentThread().getName() +
                                " service was already started"
                );
            }
        },"Worker-1 ");

        Thread worker2 = new Thread(()->{
            if (serviceStarted.compareAndSet(false, true)) {
                System.out.println(
                        Thread.currentThread().getName() +
                                " started the service"
                );
            } else {
                System.out.println(
                        Thread.currentThread().getName() +
                                " service was already started"
                );
            }
        },"Worker-2 ");

        worker1.start();
        worker2.start();

        worker1.join();
        worker2.join();



    }
}
