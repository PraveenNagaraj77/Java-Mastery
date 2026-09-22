package com.javamastery.java8.methodreferences;

public class OrderService {
    public void printOrder(Order order){
        System.out.println("Order ID: " + order.getOrderId());

        System.out.println(
                "Customer: " + order.getCustomerName()
        );

        System.out.println(
                "Amount: ₹" + order.getAmount()
        );

    }
}
