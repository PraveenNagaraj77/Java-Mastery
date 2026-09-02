package com.javamastery.oop.RideBookingSystem;

public class Ride {
    private int rideId;
    private Customer customer;
    private Driver driver;
    private Vehicle vehicle;
    private Payment payment;
    private FareCalculator fareCalculator;
    private Notification notification;

    private double distance;
    private double fare;


    public Ride(int rideId,
                Customer customer,
                Driver driver,
                Vehicle vehicle,
                Payment payment,
                Notification notification,
                double distance){
        this.rideId = rideId;
        this.customer = customer;
        this.driver = driver;
        this.vehicle = vehicle;
        this.payment = payment;
        this.notification = notification;
        this.distance = distance;

        this.fareCalculator = new FareCalculator();
    }

    public void startRide(){
        if(!driver.isAvailable()){
            System.out.println("Driver is not available");
            return;
        };
        fare = fareCalculator.calculate(vehicle,distance);

        driver.setAvailable(false);

        System.out.println("Ride Started...");
        notification.send(
                "Your ride has started."
        );
        System.out.println(
                "Ride ID      : " + rideId
        );
        System.out.println(
                "Customer     : " + customer.getName()
        );
        System.out.println(
                "Driver       : " + driver.getName()
        );
        System.out.println(
                "Distance     : " + distance + " km"
        );
        System.out.println(
                "Fare         : ₹" + fare
        );
    }

    public void completeRide() {

        System.out.println();
        System.out.println("Ride Completed.");

        payment.pay(fare);
        notification.send(
                "Ride completed. Payment successful."
        );

        driver.setAvailable(true);

        System.out.println(
                "Driver is now available."
        );
    }




}
