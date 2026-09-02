package com.javamastery.oop.RideBookingSystem;

public class RideBookingSystem {

    public static void main(String[] args) {

        Customer customer =
                new Customer(
                        101,
                        "Praveen",
                        "9876543210"
                );

        Driver driver =
                new Driver(
                        501,
                        "Arun",
                        "9123456780",
                        true
                );

        Vehicle vehicle =
                new Car(
                        "TN01AB1234",
                        "Hyundai",
                        4
                );

        Payment payment =
                new UpiPayment();

        Notification notification = new EmailNotification();

        Ride ride =
                new Ride(
                        1001,
                        customer,
                        driver,
                        vehicle,
                        payment,
                        notification,
                        15
                );

        ride.startRide();

        ride.completeRide();
    }
}