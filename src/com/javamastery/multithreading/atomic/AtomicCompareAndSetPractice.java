package com.javamastery.multithreading.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCompareAndSetPractice {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger orderStatus = new AtomicInteger();

        Thread worker1 = new Thread(()->{
            boolean claimed = orderStatus.compareAndSet(0,1);
            if(claimed){
                System.out.println(
                        Thread.currentThread().getName() + "Claimed Order"
                );
            }else{
                System.out.println(
                        Thread.currentThread().getName() + " Failed to Claimed the Order"
                );
            }
        },"Order-Worker-1 ");

        Thread worker2 = new Thread(()->{
            boolean claimed = orderStatus.compareAndSet(0,1);
            if(claimed){
                System.out.println(
                        Thread.currentThread().getName() + " Claimed Order"
                );
            }else{
                System.out.println(
                        Thread.currentThread().getName() + "Failed to Claimed the Order"
                );
            }
        },"Order-Worker-2 ");

        worker1.start();
        worker2.start();

        worker1.join();
        worker2.join();
    }
}
