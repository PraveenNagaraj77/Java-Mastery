package com.javamastery.multithreading.threadcontrol;

public class InterruptPractice {
    public static void main(String[] args) throws InterruptedException {
        Thread workerThread = new Thread(()->{
            System.out.println("Worker Started");
            try{
                Thread.sleep(10000);
            }catch (InterruptedException e){
                System.out.println("Worker interrupted");
                Thread.currentThread().interrupt();
            }
        });

        workerThread.start();
        Thread.sleep(2000);
        workerThread.interrupt();
        System.out.println("Main Continues");

    }
}
