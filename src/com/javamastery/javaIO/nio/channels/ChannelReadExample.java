package com.javamastery.javaIO.nio.channels;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ChannelReadExample {
    public static void main(String[] args) {
        Path path = Path.of("orders.txt");

        try(FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)){
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (channel.read(buffer)!=-1){
                buffer.flip();

                String text = StandardCharsets.UTF_8.decode(buffer).toString();
                System.out.println(text);
            }
        }catch (IOException e){
            System.err.println("Reading failed : " + e.getMessage());
        }
    }
}
