package com.javamastery.javaIO.nio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileExample {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("data", "order.txt");

        Files.createDirectories(file.getParent());

        Files.writeString(
                file,
                "Order ID: ORD-1001\nStatus: CONFIRMED\n",
                StandardCharsets.UTF_8
        );

        String content = Files.readString(
                file,
                StandardCharsets.UTF_8
        );

        System.out.println(content);
    }
}