package com.javamastery.javaIO.fileHandling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class FileMetaDataExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("message.txt");

        System.out.println("Exists: " + Files.exists(path));
        System.out.println("Regular file: " + Files.isRegularFile(path));
        System.out.println("Readable: " + Files.isReadable(path));
        System.out.println("Size: " + Files.size(path) + " bytes");
        System.out.println("Last modified: " +
                Files.getLastModifiedTime(path));

        BasicFileAttributes attributes =
                Files.readAttributes(path, BasicFileAttributes.class);

        System.out.println("Created: " + attributes.creationTime());
        System.out.println("Directory: " + attributes.isDirectory());
        System.out.println("Regular: " + attributes.isRegularFile());
    }
}