package com.javamastery.multithreading.fundamentals;

public class ThreadLifecyclePractice {
    public static void main(String[] args) throws InterruptedException {
        Thread newThread = new Thread(()->{
            System.out.println("Task is  Running");
        });

        System.out.println(newThread.getState());
        newThread.start();
        System.out.println(newThread.getState());
        newThread.join();
        System.out.println(newThread.getState());


    }
}
