package com.javamastery.oop.RideBookingSystem;

public class FareCalculator {
    public double calculate(Vehicle vehicle,double distance){
        return vehicle.calculateFare(distance);
    }
}
