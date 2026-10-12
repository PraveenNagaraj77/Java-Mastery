package com.javamastery.javaIO.streams.characterStreams;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WriteTextExample {
    public static void main(String[] args) {
        String message = "Hello Java!\\nWelcome to Character Streams.";

        try(FileWriter writer = new FileWriter("output.txt", StandardCharsets.UTF_8)){
            writer.write(message);
            System.out.println("Text Written Successfully");
        }catch (IOException e){
            System.err.println("Unable to write text: " + e.getMessage());
        }
    }
}
