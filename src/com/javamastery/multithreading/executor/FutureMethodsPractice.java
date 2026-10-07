package com.javamastery.multithreading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureMethodsPractice {
    public static void main(String[] args) throws  Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> future = executorService.submit(()->{
            System.out.println(Thread.currentThread().getName() + " Processing Order");
            Thread.sleep(3000);
            return 1500;
        });

        System.out.println("Task Done " + future.isDone());

        Integer result = future.get();

        System.out.println("Order Total " +result);
        System.out.println("Task Done: " + future.isDone());
        System.out.println("Task Cancelled: " + future.isCancelled());

        executorService.shutdown();




    }
}
