package com.javamastery.oop.FoodDeliverySystem;

public class Order {
    private int orderId;
    private Customer customer;
    private Restaurant restaurant;
    private Payment payment;
    private Notification notification;
    private DeliveryService deliveryService;

    Order( int orderId,
           Customer customer,
           Restaurant restaurant,
           Payment payment,
           Notification notification,
           DeliveryService deliveryService){
        this.orderId = orderId;
        this.customer = customer;
        this.restaurant = restaurant;
        this.payment = payment;
        this.notification = notification;
        this.deliveryService = deliveryService;

    }

    void placeOrder(String adderess){
        System.out.println("Placing an order #"+ orderId);
        System.out.println("Customer " +customer.getName());
        System.out.println("Restaurant :"+ restaurant.getName());
        System.out.println();
        payment.processPayment();
        System.out.println();
        notification.send("Your Order has been Placed Successfully");
        deliveryService.deliver(adderess);
    }



}
