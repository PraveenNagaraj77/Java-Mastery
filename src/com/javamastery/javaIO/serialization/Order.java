package com.javamastery.javaIO.serialization;

import java.io.Serializable;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orderId;
    private String productName;
    private int quantity;

    private transient String sessionToken;

    private static String applicationName = "OrderService";

    public Order(
            String orderId,
            String productName,
            int quantity,
            String sessionToken
    ){
        this.orderId = orderId;
        this.productName = productName;
        this.quantity=quantity;
        this.sessionToken = sessionToken;
    }

    public void displayDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Product: " + productName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Session Token: " + sessionToken);
        System.out.println("Application: " + applicationName);
    }

}
