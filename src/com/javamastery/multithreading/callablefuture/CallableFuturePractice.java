package com.javamastery.multithreading.callablefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFuturePractice {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<Integer> order1 = ()->{
            System.out.println("Processing Order");
            int price = 500;
            int delivery = 50;

            return price+delivery;
        };

        Callable<Integer> order2 = ()->{
            System.out.println("Processing Order");
            int price = 800;
            int delivery = 100;

            return price+delivery;
        };



        Future<Integer> future = executorService.submit(order1);

        Future<Integer> future1 = executorService.submit(order2);

        Integer result = future.get();
        Integer result1 = future1.get();

        System.out.println(result);
        System.out.println(result1);

        executorService.shutdown();

    }
}
