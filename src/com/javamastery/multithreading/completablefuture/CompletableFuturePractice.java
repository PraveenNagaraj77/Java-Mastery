
package com.javamastery.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFuturePractice {

    // Simulate fetching payment information asynchronously
    static CompletableFuture<String> fetchPayment(String orderId) {
        return CompletableFuture.supplyAsync(
                () -> "Payment successful for " + orderId
        );
    }

    // Simulate fetching delivery information asynchronously
    static CompletableFuture<String> fetchDelivery(String orderId) {
        return CompletableFuture.supplyAsync(
                () -> "Delivery scheduled for " + orderId
        );
    }

    public static void main(String[] args) {

        // 1. Create an order ID asynchronously
        CompletableFuture<String> orderFuture =
                CompletableFuture.supplyAsync(() -> "ORD-101");

        // 2. Transform the order ID into a confirmation message
        CompletableFuture<String> confirmedFuture =
                orderFuture.thenApply(
                        orderId -> "Order confirmed: " + orderId
                );

        // 3. Chain a dependent asynchronous operation
        CompletableFuture<String> paymentFuture =
                orderFuture.thenCompose(
                        orderId -> fetchPayment(orderId)
                );

        // 4. Combine two independent asynchronous results
        CompletableFuture<String> deliveryFuture =
                orderFuture.thenCompose(
                        orderId -> fetchDelivery(orderId)
                );

        CompletableFuture<String> finalOrderFuture =
                paymentFuture.thenCombine(
                        deliveryFuture,
                        (payment, delivery) ->
                                "Order ready: " + payment + ", " + delivery
                );

        // 5. Retrieve and print the results
        System.out.println(confirmedFuture.join());
        System.out.println(finalOrderFuture.join());
    }
}
