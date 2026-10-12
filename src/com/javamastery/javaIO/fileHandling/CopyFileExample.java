package com.javamastery.javaIO.fileHandling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopyFileExample {
    public static void main(String[] args)throws IOException {
        Path source = Paths.get("message.txt");
        Path destination = Paths.get("message-copy.txt");

        Files.copy(source,destination, StandardCopyOption.REPLACE_EXISTING);

        System.out.println("File Copied");

    }
}
