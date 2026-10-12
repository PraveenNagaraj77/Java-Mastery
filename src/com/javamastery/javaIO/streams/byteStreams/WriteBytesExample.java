package com.javamastery.javaIO.streams.byteStreams;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteBytesExample {
    public static void main(String[] args) {
        byte[] data = {65,66,67};

        try(FileOutputStream output = new FileOutputStream("output.txt")){
            output.write(data);
            System.out.println("Bytes Written Successfully");
        } catch (IOException e) {
            System.err.println("Unable to write : " + e.getMessage());
        }

    }
}
