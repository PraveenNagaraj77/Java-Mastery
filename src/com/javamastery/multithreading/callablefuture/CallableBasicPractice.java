package com.javamastery.multithreading.callablefuture;

import java.util.concurrent.Callable;

public class CallableBasicPractice {
    public static void main(String[] args) throws  Exception {
        Callable<Integer> orderTask = ()->{
            System.out.println("Calcualting Order Total...");
            int price = 400;
            int deliveryFee = 50;

            return price+deliveryFee;
        };

        Integer result = orderTask.call();
        System.out.println("Order Total :" + result);
    }
}
