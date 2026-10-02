package com.javamastery.multithreading.communication;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerPractice {
    public static void main(String[] args) throws InterruptedException {
        Queue<String> orderQueue = new LinkedList<>();
        Object lock = new Object();

        Thread producer = new Thread(()->{
            synchronized (lock){
                for (int i = 101; i <=105; i++) {
                    orderQueue.add("ORDER-"+i);
                }
                System.out.println(orderQueue);
                lock.notifyAll();
            }
        });

        Thread consumer = new Thread(()->{
           synchronized (lock){
               while (orderQueue.isEmpty()){
                   try{
                       lock.wait();
                   } catch (InterruptedException e) {
                       Thread.currentThread().interrupt();
                   }
               }
               while (!orderQueue.isEmpty()){
                   String orders = orderQueue.poll();
                   System.out.println("Consumed : " +orders);
               }


           }
        });


        consumer.start();
        producer.start();


        consumer.join();
        producer.join();
    }
}
