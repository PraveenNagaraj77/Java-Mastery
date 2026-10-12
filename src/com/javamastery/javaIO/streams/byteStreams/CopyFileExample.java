package com.javamastery.javaIO.streams.byteStreams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFileExample {
    public static void main(String[] args) {
        String source  = "sample.txt";
        String destination = "copied.txt";

        try(FileInputStream input = new FileInputStream(source);
            FileOutputStream output = new FileOutputStream(destination)
        ) {
            int data;

            while ((data = input.read())!=-1){
                output.write(data);
            }

            System.out.println("File Copied Successfully");

        } catch (IOException e) {
            System.err.println("File copy failed: " + e.getMessage());
        }

    }
}
