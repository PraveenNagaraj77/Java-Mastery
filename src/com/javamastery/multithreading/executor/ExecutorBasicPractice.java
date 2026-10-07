package com.javamastery.multithreading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorBasicPractice {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(()->{
            System.out.println(Thread.currentThread().getName() + " Processing Order");
        });

        executorService.execute(()->{
            System.out.println(Thread.currentThread().getName() + " Processing Order");
        });

        executorService.execute(()->{
            System.out.println(Thread.currentThread().getName() + " Processing Order");
        });
        executorService.execute(()->{
            System.out.println(Thread.currentThread().getName() + " Processing Order");
        });

        executorService.execute(()->{
            System.out.println(Thread.currentThread().getName() + " Processing Order");
        });


        executorService.shutdown();

    }
}
