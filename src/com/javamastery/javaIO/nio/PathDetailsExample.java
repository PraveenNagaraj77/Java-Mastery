package com.javamastery.javaIO.nio;

import java.nio.file.Path;

public class PathDetailsExample {
    public static void main(String[] args) {
        Path relativePath = Path.of("data", "orders.txt");

        System.out.println("Relative: " + relativePath);
        System.out.println("Absolute: " + relativePath.toAbsolutePath());
        System.out.println("File name: " + relativePath.getFileName());
        System.out.println("Parent: " + relativePath.getParent());
    }
}