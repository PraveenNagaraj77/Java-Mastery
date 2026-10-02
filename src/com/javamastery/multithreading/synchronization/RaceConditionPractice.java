package com.javamastery.multithreading.synchronization;

public class RaceConditionPractice {
    static int counter = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                synchronized (RaceConditionPractice.class){
                    counter++;
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                synchronized (RaceConditionPractice.class){
                    counter++;
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter);


    }
}
