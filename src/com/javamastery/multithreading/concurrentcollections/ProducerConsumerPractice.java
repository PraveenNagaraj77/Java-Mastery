package com.javamastery.multithreading.concurrentcollections;

import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumerPractice {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(2);

        Thread producer = new Thread(()->{
            try{
                queue.put("ORD-101");
                System.out.println("Produced: ORD-101");

                queue.put("ORD-102");
                System.out.println("Produced: ORD-102");

                queue.put("ORD-103");
                System.out.println("Produced: ORD-103");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(()->{
            try{
                for (int i = 0; i < 3; i++) {
                    String order = queue.take();
                    System.out.println("Consumed : " + order);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });


        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("ALL ORDERS Processed");


    }
}
