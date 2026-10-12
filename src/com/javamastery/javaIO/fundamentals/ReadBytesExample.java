package com.javamastery.javaIO.fundamentals;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadBytesExample {

    public static void main(String[] args) {
        try (FileInputStream input =
                     new FileInputStream("sample.txt")) {

            int data;

            while ((data = input.read()) != -1) {
                System.out.println((char) data);
            }

        } catch (IOException e) {
            System.err.println(
                    "Unable to read file: " + e.getMessage()
            );
        }
    }
}