package com.javamastery.multithreading.threadcreation;

public class ThreadCreationPractice {



    public static void main(String[] args) {
        class PaymentThread extends Thread {
            @Override
            public void run() {
                System.out.println("Processing Payment");
            }
        }
        PaymentThread thread = new PaymentThread();
        thread.start();

        class SendEmailTask implements Runnable{
            @Override
            public void run() {
                System.out.println("Sending Email...");
            }
        }

        SendEmailTask task = new SendEmailTask();
        Thread thread1 = new Thread(task);
        thread1.start();

        Runnable task1 = ()->{
            System.out.println("Generating Report");
        };
        Thread thread2 = new Thread(task1);
        thread2.start();
    }
}
