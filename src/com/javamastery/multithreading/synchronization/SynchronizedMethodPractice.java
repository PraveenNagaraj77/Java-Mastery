package com.javamastery.multithreading.synchronization;

public class SynchronizedMethodPractice {
    static int stock = 10;

    public synchronized void sellItem(){
        if(stock>0){
            stock--;
            System.out.println(Thread.currentThread().getName() + " Sold an Item . Stock : " + stock);
        }else{
            System.out.println("Out of Stock");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedMethodPractice shop = new SynchronizedMethodPractice();

        Thread customer1 = new Thread(()->{
            for (int i = 0; i < 6; i++) {
                shop.sellItem();
            }
        },"Customer-1");

        Thread customer2 = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                shop.sellItem();
            }
        }, "Customer-2");

        customer1.start();
        customer2.start();

        customer1.join();
        customer2.join();

        System.out.println("Final Stock: " + stock);

    }
}
