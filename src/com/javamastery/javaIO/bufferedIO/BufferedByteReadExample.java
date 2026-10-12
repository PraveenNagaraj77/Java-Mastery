package com.javamastery.javaIO.bufferedIO;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedByteReadExample {
    public static void main(String[] args) {
        try(BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("input.bin"))) {
            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead=inputStream.read())!=-1){
                System.out.println("Read"+bytesRead+"bytes");
            }

        }catch (IOException e){
            System.err.println("Read Failed : " + e.getMessage());
        }
    }
}
