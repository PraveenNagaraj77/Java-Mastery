package com.javamastery.multithreading.concurrentcollections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueComparisonPractice {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue1 = new ArrayBlockingQueue<>(3);

        BlockingQueue<String> queue2 = new LinkedBlockingQueue<>(3);

        queue1.put("ORD-101");
        queue1.put("ORD-102");
        queue1.put("ORD-103");

        queue2.put("ORD-101");
        queue2.put("ORD-102");
        queue2.put("ORD-103");

        System.out.println("Array queue: " + queue1);
        System.out.println("Linked queue: " + queue2);

        System.out.println("Removed: " + queue1.take());
        System.out.println("Removed: " + queue2.take());

        System.out.println("Array remaining: " + queue1.remainingCapacity());
        System.out.println("Linked remaining: " + queue2.remainingCapacity());

    }
}
