package com.javamastery.javaIO.serialization;

import java.io.*;

public class DeserializationExample {
    public static void main(String[] args) {
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream("order.ser"))) {
            Order order = (Order) input.readObject();
            System.out.println("Order deserialized successfully");
            order.displayDetails();
        }catch (IOException e){
            System.err.println(
                    "Deserialization failed: " + e.getMessage()
            );
        }catch (ClassNotFoundException e){
            System.err.println("Order class could not be found : " +e.getMessage());
        }
    }
}
