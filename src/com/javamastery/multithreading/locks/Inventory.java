package com.javamastery.multithreading.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Inventory {
    private int stock = 10;
    private final Lock lock = new ReentrantLock();

    public void sellItem(){
        lock.lock();
        try{
            if(stock>0){
                stock--;
                System.out.println(Thread.currentThread().getName() + "Sold Item . Stock" + stock);
            }else {
                System.out.println("Out of Stock");
            }
        }finally {
            lock.unlock();
        }
    }



}
