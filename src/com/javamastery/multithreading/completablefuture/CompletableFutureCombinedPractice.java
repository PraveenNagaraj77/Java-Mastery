package com.javamastery.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureCombinedPractice {

    public static void main(String[] args) {

        // 1. Create three asynchronous tasks
        CompletableFuture<String> payment =
                CompletableFuture.supplyAsync(() -> "Payment verified");

        CompletableFuture<String> inventory =
                CompletableFuture.supplyAsync(() -> "Inventory reserved");

        CompletableFuture<String> delivery =
                CompletableFuture.supplyAsync(() -> "Delivery scheduled");

        // 2. Wait until all three tasks complete
        CompletableFuture<Void> allTasks =
                CompletableFuture.allOf(payment, inventory, delivery);

        allTasks.join();

        // 3. Print the results
        System.out.println("Payment: " + payment.join());
        System.out.println("Inventory: " + inventory.join());
        System.out.println("Delivery: " + delivery.join());

        // 4. Create a future that fails
        CompletableFuture<String> failedService =
                CompletableFuture.supplyAsync(() -> {
                    throw new RuntimeException("Payment service failed");
                });

        // 5. Handle the exception with a fallback value
        CompletableFuture<String> handledService =
                failedService.exceptionally(ex ->
                        "Service temporarily unavailable"
                );

        // 6. Print the fallback result
        System.out.println("Fallback: " + handledService.join());
    }
}