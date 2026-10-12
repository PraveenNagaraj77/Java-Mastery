package com.javamastery.javaIO.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class FileMetadataExample {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("data", "order.txt");

        System.out.println("Exists: " + Files.exists(path));
        System.out.println("Is regular file: " + Files.isRegularFile(path));
        System.out.println("Is directory: " + Files.isDirectory(path));
        System.out.println("Readable: " + Files.isReadable(path));

        if (Files.exists(path)) {
            BasicFileAttributes attributes =
                    Files.readAttributes(
                            path,
                            BasicFileAttributes.class
                    );

            System.out.println("Size: " + attributes.size());
            System.out.println("Created: " + attributes.creationTime());
            System.out.println("Modified: " + attributes.lastModifiedTime());
        }
    }
}
