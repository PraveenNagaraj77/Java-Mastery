package com.javamastery.multithreading.communication;

public class WaitNotifyPractice {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread worker = new Thread(()->{
            synchronized (lock){
                System.out.println("Worker waiting");
                try{
                    lock.wait();
                    System.out.println("Worker Resumed");
                }catch (InterruptedException e){
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
               System.out.println("Notifier Sending Notification");
               lock.notify();
           }
        });

        worker.start();
        notifier.start();

        worker.join();
        notifier.join();

    }
}
