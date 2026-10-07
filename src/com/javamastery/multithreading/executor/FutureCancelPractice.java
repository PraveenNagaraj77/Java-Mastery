package com.javamastery.multithreading.executor;

import com.javamastery.exceptionHandling.ThrowExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCancelPractice {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> future = executorService.submit(()->{
           try{
               System.out.println(Thread.currentThread().getName() + " Processing Order...");

               for (int i = 0; i <=10 ; i++) {
                   System.out.println("Processing step " + i);
                   Thread.sleep(1000);
               }
               return 1500;

           }catch (InterruptedException e){
               System.out.println("Order Processing Cancelled");
               Thread.currentThread().interrupt();
               return -1;
           }
        });

        Thread.sleep(2500);

        System.out.println("Cancelling ORDER");

        boolean cancelled = future.cancel(true);

        System.out.println("Cancel Result: " + cancelled);
        System.out.println("Is Cancelled: " + future.isCancelled());
        System.out.println("Is Done: " + future.isDone());

        executorService.shutdown();

    }
}
