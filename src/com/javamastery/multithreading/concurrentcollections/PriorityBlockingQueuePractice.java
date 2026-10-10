package com.javamastery.multithreading.concurrentcollections;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueuePractice {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();

        queue.put(30);
        queue.put(10);
        queue.put(20);

        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());

    }
}
