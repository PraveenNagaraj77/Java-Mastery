package com.javamastery.multithreading.callablefuture;

import java.util.concurrent.*;

public class FutureTimeoutPractice {
    public static void main(String[] args) throws ExecutionException,InterruptedException,TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<Integer> payment1 = ()->{
            System.out.println("Processing Payment");
            Thread.sleep(5000);
            return 2000;
        };

        Future<Integer> future = executorService.submit(payment1);

        try{
            Integer result = future.get(2, TimeUnit.SECONDS);
            System.out.println("Result : " + result);
        }catch (TimeoutException e){
            System.out.println("Payment processing timed out");
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println("Task Failed");
        }

        System.out.println("Cancelled: " + future.isCancelled());
        System.out.println("Done: " + future.isDone());


        executorService.shutdown();

    }
}
