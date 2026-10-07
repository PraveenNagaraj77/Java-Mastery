package com.javamastery.multithreading.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockPractice {

    static Lock paymentLock = new ReentrantLock();

    public static  void processPayment() {
        try {

            if (paymentLock.tryLock(5, TimeUnit.SECONDS)) {

                try {
                    System.out.println(
                            Thread.currentThread().getName()
                                    + " Processing Payment..."
                    );

                    Thread.sleep(3000);

                } finally {
                    paymentLock.unlock();
                }

            } else {
                System.out.println(
                        Thread.currentThread().getName()
                                + " Payment Terminal Busy"
                );
            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println(
                    Thread.currentThread().getName()
                            + " Payment interrupted"
            );
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread customer1 = new Thread(
                TryLockPractice::processPayment,"Customer-1"
        );

        Thread customer2 = new Thread(
                TryLockPractice::processPayment,
                "Customer-2"
        );

        Thread customer3 = new Thread(
                TryLockPractice::processPayment,
                "Customer-3"
        );

        customer1.start();
        Thread.sleep(100);

        customer2.start();
        customer3.start();

        customer1.join();
        customer2.join();
        customer3.join();


    }
}
