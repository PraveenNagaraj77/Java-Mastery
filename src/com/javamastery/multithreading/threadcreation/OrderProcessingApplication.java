package com.javamastery.multithreading.threadcreation;

public class OrderProcessingApplication {
    public static void main(String[] args) {
        Runnable paymentTask = ()->{
            System.out.println("Processing Payment");
        };

        Runnable inventoryTask = () -> {
            System.out.println("Updating inventory...");
        };

        Runnable notificationTask = () -> {
            System.out.println("Sending order confirmation...");
        };

        Thread paymentThread = new Thread(paymentTask);
        Thread inventoryThread = new Thread(inventoryTask);
        Thread notificationThread = new Thread(notificationTask);

        paymentThread.start();
        inventoryThread.start();
        notificationThread.start();

    }
}
