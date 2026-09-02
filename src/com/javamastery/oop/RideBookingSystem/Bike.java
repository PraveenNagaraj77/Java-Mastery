package com.javamastery.oop.RideBookingSystem;

public class Bike extends Vehicle{
    public Bike(String vehicleNumber,String brand){

        super(vehicleNumber,brand);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * 10;
    }
}
