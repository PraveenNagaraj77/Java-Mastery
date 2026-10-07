package com.javamastery.multithreading.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairLockPractice {
    static Lock fairlock = new ReentrantLock(true);

    public static void processFairWorkers() {

        fairlock.lock();

        try {
            System.out.println(
                    Thread.currentThread().getName() + " Acquired Lock"
            );

            Thread.sleep(500);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println(
                    Thread.currentThread().getName() + " Interrupted"
            );

        } finally {

            fairlock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread worker1 = new Thread(FairLockPractice::processFairWorkers,"Worker-1");

        Thread worker2 = new Thread(FairLockPractice::processFairWorkers,"Worker-2");


        Thread worker3 = new Thread(FairLockPractice::processFairWorkers,"Worker-3");


        Thread worker4 = new Thread(FairLockPractice::processFairWorkers,"Worker-4");

        Thread worker5 = new Thread(FairLockPractice::processFairWorkers,"Worker-5");


        worker1.start();
        worker2.start();
        worker3.start();
        worker4.start();
        worker5.start();

        worker1.join();
        worker2.join();
        worker3.join();
        worker4.join();
        worker5.join();

    }

}
