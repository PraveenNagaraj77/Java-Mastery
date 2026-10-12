package com.javamastery.javaIO.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializationExample {
    public static void main(String[] args) {
        Order order = new Order(
                "ORD-101",
                "Wireless Keyboard",
                2,
                "TEMP-SECRET-123"
        );

        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("order.ser"))){
            output.writeObject(order);
            System.out.println("Order Serialized Successfully..");

        }catch (IOException e){
            System.err.println("Serializaion failed : " + e.getMessage());
        }

    }
}
