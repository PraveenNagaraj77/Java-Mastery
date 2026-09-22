package com.javamastery.java8.methodreferences;

public class Order {
    private int orderId;
    private String customerName;
    private double amount;

    public Order(
            int orderId,
            String customerName,
            double amouunt
    ){
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
    }

    public Order(String customerName){
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }




}
