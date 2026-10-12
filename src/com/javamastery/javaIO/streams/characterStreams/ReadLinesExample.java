package com.javamastery.javaIO.streams.characterStreams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ReadLinesExample {
    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new FileReader("sample.txt",StandardCharsets.UTF_8))) {
            String line;

            while ((line=reader.readLine())!=null){
                System.out.println(line);
            }

        }catch (IOException e){
            System.err.println("Unable to Read the File" + e.getMessage());
        }
    }
}
