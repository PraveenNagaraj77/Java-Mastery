package com.javamastery.multithreading.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorSubmitPractice {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<Integer> order1 =  executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + " Processing Order");
            return 500;
        });

        Future<Integer> order2 =  executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + " Processing Order");
            return 750;
        });

        Future<Integer> order3 =  executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + " Processing Order");
            return 1000;
        });

        System.out.println(order1.get());
        System.out.println(order2.get());
        System.out.println(order3.get());

        executorService.shutdown();

    }
}
