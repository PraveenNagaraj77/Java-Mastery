package com.javamastery.multithreading.communication;

public class NotifyAllPractice {
    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();

        Thread worker1 = new Thread(()->{
            synchronized (lock) {
                System.out.println("Worker-1 : Waiting");

                try {
                    lock.wait();
                    System.out.println("Worker-1 : Resumed");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread worker2 = new Thread(()->{
            synchronized (lock) {
                System.out.println("Worker-2 : Waiting");

                try {
                    lock.wait();
                    System.out.println("Worker-2 : Resumed");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread worker3 = new Thread(()->{
            synchronized (lock) {
                System.out.println("Worker-3 : Waiting");

                try {
                    lock.wait();
                    System.out.println("Worker-3 : Resumed");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread notifier = new Thread(()->{
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            synchronized (lock){
                System.out.println("Notifying all workers");
                lock.notifyAll();
            }
        });

        worker1.start();
        worker2.start();
        worker3.start();
        notifier.start();

        worker1.join();
        worker2.join();
        worker3.join();
        notifier.join();



    }
}
