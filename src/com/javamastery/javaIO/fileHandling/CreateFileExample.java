package com.javamastery.javaIO.fileHandling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFileExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("data","sample.txt");

        Files.createDirectories(path.getParent());

        if(Files.notExists(path)){
            Files.createFile(path);
            System.out.println("File Created");
        }else{
            System.out.println("File already exists");
        }
    }
}
