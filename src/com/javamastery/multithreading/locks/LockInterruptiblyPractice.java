package com.javamastery.multithreading.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyPractice {
    static Lock workerLock = new ReentrantLock();

    public static void processWorker(){
        try {
            workerLock.lockInterruptibly();

            try {
                System.out.println(
                        Thread.currentThread().getName() + " Processing Worker"
                );

                Thread.sleep(5000);

            } finally {
                workerLock.unlock();
            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println(
                    Thread.currentThread().getName()
                            + " Interrupted while waiting"
            );
        }
        }

    public static void main(String[] args) throws InterruptedException {
        Thread customer1 = new Thread(
               LockInterruptiblyPractice::processWorker,"Customer-1"
        );

        Thread customer2 = new Thread(
                LockInterruptiblyPractice::processWorker,
                "Customer-2"
        );

        Thread customer3 = new Thread(
                LockInterruptiblyPractice::processWorker,
                "Customer-3"
        );

        customer1.start();

        Thread.sleep(100);

        customer2.start();
        customer3.start();

        Thread.sleep(1000);

        customer2.interrupt();

        customer1.join();
        customer2.join();
        customer3.join();
    }
}
