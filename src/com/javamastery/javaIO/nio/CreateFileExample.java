package com.javamastery.javaIO.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateFileExample {
    public static void main(String[] args) throws IOException {
        Path directory = Path.of("data", "orders");
        Path file = directory.resolve("order-1001.txt");

        Files.createDirectories(directory);

        if (Files.notExists(file)) {
            Files.createFile(file);
        }

        System.out.println("File location: " + file.toAbsolutePath());
    }
}