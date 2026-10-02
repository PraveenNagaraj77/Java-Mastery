package com.javamastery.multithreading.synchronization;

public class ObjectVsClassLockPractice {

    static int stock = 10;

    public void sellItem(){
        synchronized (ObjectVsClassLockPractice.class){
            System.out.println(Thread.currentThread().getName() + " entered");
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(Thread.currentThread().getName() + "leaving");
        }


    }

    public static void main(String[] args) throws InterruptedException {
        ObjectVsClassLockPractice shop1 = new ObjectVsClassLockPractice();

        ObjectVsClassLockPractice shop2 = new ObjectVsClassLockPractice();

        Thread t1 = new Thread(()->shop1.sellItem(),"Thread-1");

        Thread t2 = new Thread(()->shop2.sellItem(),"Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();


    }
}
