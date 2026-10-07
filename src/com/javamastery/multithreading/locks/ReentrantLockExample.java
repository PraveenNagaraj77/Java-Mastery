package com.javamastery.multithreading.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    static int stock = 18;
    static Lock lock = new ReentrantLock();

    public static void sellItem(){
        lock.lock();
        try{
            if (stock>0){
                stock--;
                System.out.println(Thread.currentThread().getName() + " Sold Item . Stock " +stock);
            }else {
                System.out.println("Out of Stock");
            }
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread customer1 = new Thread(()->{
            for (int i = 0; i <6; i++) {
                sellItem();
            }
        },"Customer-1");

        Thread customer2 = new Thread(()->{
            for (int i = 0; i < 6; i++) {
                sellItem();
            }
        },"Customer-2");

        customer1.start();
        customer2.start();

        customer1.join();
        customer2.join();

        System.out.println("Final Stock" + stock);

    }
}
