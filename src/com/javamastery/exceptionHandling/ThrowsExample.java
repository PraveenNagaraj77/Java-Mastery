package com.javamastery.exceptionHandling;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ThrowsExample {
    static void readFile() throws FileNotFoundException {
        FileReader reader = new FileReader("data.txt");
    }
    public static void main(String[] args) {
        try{
            readFile();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("File Could not be Opened");
        }
    }
}
