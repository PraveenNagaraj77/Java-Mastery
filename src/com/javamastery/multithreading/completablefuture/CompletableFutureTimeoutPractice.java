package com.javamastery.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTimeoutPractice {

    public static void main(String[] args) {

        // Example 1: orTimeout()
        CompletableFuture<String> paymentFuture =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }

                    return "Payment successful";
                });

        try {
            String result = paymentFuture
                    .orTimeout(1, TimeUnit.SECONDS)
                    .join();

            System.out.println("Result: " + result);

        } catch (RuntimeException e) {
            System.out.println("Payment service timed out");
        }

        // Example 2: completeOnTimeout()
        CompletableFuture<String> inventoryFuture =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }

                    return "Inventory reserved";
                });

        String inventoryResult = inventoryFuture
                .completeOnTimeout(
                        "Inventory status unavailable",
                        1,
                        TimeUnit.SECONDS
                )
                .join();

        System.out.println("Inventory: " + inventoryResult);
    }
}