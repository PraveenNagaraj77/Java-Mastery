package com.javamastery.multithreading.threadcontrol;

public class JoinPractice {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(()->{
            System.out.println("Task Started");
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            System.out.println("Task Completed");
        });
        worker.start();
        worker.join();

        System.out.println("Main Continues after worker completion");
    }
}
