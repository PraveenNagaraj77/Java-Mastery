package com.javamastery.exceptionHandling;

import java.io.FileReader;
import java.io.IOException;

public class TryWithResourcesExample {
    public static void main(String[] args) {
        try(FileReader reader = new FileReader("data.txt")) {
            int data = reader.read();
            System.out.println((char) data);
        }catch (IOException e){
            System.out.println("File Error : " +e.getMessage());
        }
    }
}

