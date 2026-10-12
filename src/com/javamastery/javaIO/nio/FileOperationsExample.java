package com.javamastery.javaIO.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileOperationsExample {
    public static void main(String[] args) throws IOException {
        Path source = Path.of("data", "order.txt");
        Path backup = Path.of("data", "order-backup.txt");
        Path archive = Path.of("data", "archive", "order.txt");

        Files.copy(
                source,
                backup,
                StandardCopyOption.REPLACE_EXISTING
        );

        Files.createDirectories(archive.getParent());

        Files.move(
                backup,
                archive,
                StandardCopyOption.REPLACE_EXISTING
        );

        boolean deleted = Files.deleteIfExists(archive);

        System.out.println("Deleted: " + deleted);
    }
}