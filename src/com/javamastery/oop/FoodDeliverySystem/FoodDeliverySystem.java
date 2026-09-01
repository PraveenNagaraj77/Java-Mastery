package com.javamastery.oop.FoodDeliverySystem;

public class FoodDeliverySystem {
    public static void main(String[] args) {
        Customer customer = new Customer(101,"Praveen","9150782176");

        Restaurant restaurant =
                new Restaurant(
                        501,
                        "Chennai Biryani House"
                );

        Payment payment =
                new CardPayment(
                        450,
                        "1234567812345678"
                );

        Notification notification =
                new EmailNotification();

        DeliveryService delivery =
                new BikeDelivery();


        Order order =
                new Order(
                        1001,
                        customer,
                        restaurant,
                        payment,
                        notification,
                        delivery
                );

        order.placeOrder(
                "Perungudi, Chennai"
        );
    }
}
