package com.javamastery.multithreading;

public class MultiThreadExample {
    public static void main(String[] args) {
            Thread orderThread = new Thread(()->{
                System.out.println("Processing Order");
            });

            Thread paymentThread = new Thread(()->{
                System.out.println("Processing Payment");
            });

            orderThread.start();
            paymentThread.start();

    }
}
