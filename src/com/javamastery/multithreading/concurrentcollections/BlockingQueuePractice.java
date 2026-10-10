package com.javamastery.multithreading.concurrentcollections;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueuePractice {
    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue<String> arrBlock = new ArrayBlockingQueue<>(2);

        arrBlock.put("ORD-101");
        arrBlock.put("ORD-102");

        System.out.println("Queue : " + arrBlock );

        System.out.println(arrBlock.take());

        System.out.println("Remaining : " + arrBlock);

    }
}
