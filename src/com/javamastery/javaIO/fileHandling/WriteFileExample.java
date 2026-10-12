package com.javamastery.javaIO.fileHandling;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteFileExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("message.txt");

        Files.writeString(
                path,
                "Hello Java!\nFile handling is easy.",
                StandardCharsets.UTF_8
        );

        System.out.println("Text saved.");
    }
}
