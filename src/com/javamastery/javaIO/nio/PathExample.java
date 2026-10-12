package com.javamastery.javaIO.nio;

import java.nio.file.Path;

public class PathExample {
    public static void main(String[] args) {
        Path path1 = Path.of("reports","sales.txt");
        Path path2 = Path.of("reports","sales.txt");

        System.out.println(path1);
        System.out.println(path2);

        System.out.println(path1.equals(path2));
    }
}
