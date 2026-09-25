package com.javamastery.java8.streamapi;

public class Order {

    private int orderId;
    private String status;
    private double amount;

    public Order(
            int orderId,
            String status,
            double amount) {

        this.orderId = orderId;
        this.status = status;
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }
}