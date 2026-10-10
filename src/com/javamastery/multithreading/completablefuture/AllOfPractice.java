package com.javamastery.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;

public class AllOfPractice {

    public static void main(String[] args) {

        CompletableFuture<String> payment =
                CompletableFuture.supplyAsync(() -> "Payment verified");

        CompletableFuture<String> inventory =
                CompletableFuture.supplyAsync(() -> "Inventory reserved");

        CompletableFuture<String> delivery =
                CompletableFuture.supplyAsync(() -> "Delivery scheduled");

        CompletableFuture<Void> allTasks =
                CompletableFuture.allOf(payment, inventory, delivery);

        allTasks.join();

        System.out.println(payment.join());
        System.out.println(inventory.join());
        System.out.println(delivery.join());
    }
}