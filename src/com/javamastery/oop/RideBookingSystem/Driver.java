package com.javamastery.oop.RideBookingSystem;

public class Driver {
    public int driverId;
    private String name;
    private String phoneNumber;
    private boolean available;

    public Driver(
            int driverId,
            String name,
            String phoneNumber,
            boolean available
    ){
        this.driverId = driverId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.available = available;
    }


    public int getDriverId() {
        return driverId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


    public void displayDriver() {

        System.out.println("Driver ID   : " + driverId);
        System.out.println("Name        : " + name);
        System.out.println("Phone       : " + phoneNumber);
        System.out.println("Available   : " + available);
    }

}
