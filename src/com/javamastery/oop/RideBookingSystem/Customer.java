package com.javamastery.oop.RideBookingSystem;

public class Customer {
    private int customerId;
    private String name;
    private String phoneNumber;

    public Customer(int customerId,String name,String phoneNumber){
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getCutsomerId(){
        return customerId;
    }

    public String getName(){
        return name;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void displayCustomer(){
        System.out.println("Customer ID : " + customerId);
        System.out.println("Name        : " + name);
        System.out.println("Phone       : " + phoneNumber);
    }


}
