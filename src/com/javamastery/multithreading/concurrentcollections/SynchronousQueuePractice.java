package com.javamastery.multithreading.concurrentcollections;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueuePractice {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        Thread producer = new Thread(()->{
           try{
               System.out.println("Producing Order");
               queue.put("ORD-101");
               System.out.println("Order Handed Off");
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
           }
        });

        Thread consumer = new Thread(()->{
            try{
                Thread.sleep(1000);
                System.out.println("Consumed : " + queue.take());
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

    }
}
