package com.javamastery.multithreading.threadcontrol;

public class SleepPractice {
    public static void main(String[] args) {
        Runnable task1 = ()->{
            System.out.println("Payment Started");
            try{
                Thread.sleep(2000);
                System.out.println("Payment Completed");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread thread1 = new Thread(task1);
        thread1.start();


        Runnable task2 = () -> {

            System.out.println("Email Started");

            try {
                Thread.sleep(1000);
                System.out.println("Email Sent");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread thread2 = new Thread(task2);
        thread2.start();

    }
}
