package com.javamastery.multithreading.Volatile;

public class VolatilePractice {
    static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {

        Thread worker = new Thread(()->{
            while (running){
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            System.out.println("Worker Stopped");
        });

        worker.start();
        Thread.sleep(2000);
        running=false;
        worker.join();
        System.out.println("Main Completed");
    }

}
