package com.javamastery.javaIO.bufferedIO;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedByteWriteExample {
    public static void main(String[] args) {
        byte[] data = {65, 66, 67};

        try (BufferedOutputStream output = new BufferedOutputStream(
                new FileOutputStream("output.bin"))) {

            output.write(data);

            System.out.println("Bytes written successfully.");

        } catch (IOException e) {
            System.err.println("Write failed: " + e.getMessage());
        }
    }
}