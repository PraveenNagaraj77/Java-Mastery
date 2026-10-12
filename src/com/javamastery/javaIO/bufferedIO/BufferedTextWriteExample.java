package com.javamastery.javaIO.bufferedIO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BufferedTextWriteExample {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("report.txt", StandardCharsets.UTF_8))) {

            writer.write("Daily Report");
            writer.newLine();

            writer.write("Orders processed: 120");
            writer.newLine();

            writer.write("Status: Completed");
            writer.newLine();

            System.out.println("Report created successfully.");

        } catch (IOException e) {
            System.err.println("Write failed: " + e.getMessage());
        }
    }
}