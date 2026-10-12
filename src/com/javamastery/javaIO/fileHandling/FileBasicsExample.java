package com.javamastery.javaIO.fileHandling;

import java.io.File;

public class FileBasicsExample {
    public static void main(String[] args) {
        File file = new File("sample.txt");

        System.out.println("Exists : " + file.exists());
        System.out.println("IS File : " + file.isFile());
        System.out.println("Is Directory : " + file.isDirectory());
        System.out.println("Absolute Path : "+file.getAbsolutePath());
        System.out.println("Name: "+file.getName());

        File directory = new File("reports");
        if(directory.mkdir()){
            System.out.println("Directory created");
        }else{
            System.out.println("Directory not created");
        }


    }
}
