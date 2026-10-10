package com.javamastery.multithreading.callablefuture;

import java.util.concurrent.*;

public class CallableExceptionPractice {
    public static void main(String[] args) throws ExecutionException,InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<Integer> successOrder = ()->{
            return 500;
        };

        Callable<Integer> failedOrder = ()->{
            throw new IllegalArgumentException("Invalid Order Amount");
        };

        Future<Integer> successFulFuture = executorService.submit(successOrder);

        Future<Integer> failedFuture = executorService.submit(failedOrder);

        try {
            Integer result = successFulFuture.get();
            System.out.println(result);

        } catch (InterruptedException e) {
            System.out.println("Waiting thread was interrupted");

        } catch (ExecutionException e) {
            System.out.println("Order processing failed");
            System.out.println("Cause: " + e.getCause());
        }

        try {
            Integer result1 = failedFuture.get();
            System.out.println(result1);

        } catch (InterruptedException e) {
            System.out.println("Waiting thread was interrupted");

        } catch (ExecutionException e) {
            System.out.println("Order processing failed");
            System.out.println("Cause: " + e.getCause());
        }

        executorService.shutdown();


    }
}
