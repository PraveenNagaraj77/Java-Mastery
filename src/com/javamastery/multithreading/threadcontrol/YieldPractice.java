package com.javamastery.multithreading.threadcontrol;

public class YieldPractice {
    public static void main(String[] args) {
        Thread worker1 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("Worker 1 : " + i);
                Thread.yield();
            }
        });

        worker1.start();

        Thread worker2 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("Worker 2 : " + i);
                Thread.yield();
            }
        });

        worker2.start();

    }
}
