package com.javamastery.javaIO.nio.channels;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ChannelCopyExample {
    public static void main(String[] args) {
        Path sourcePath = Path.of("orders.txt");
        Path destinationPath = Path.of("orders-backup.txt");

        try(FileChannel source = FileChannel.open(sourcePath, StandardOpenOption.READ);
            FileChannel destination = FileChannel.open(destinationPath,StandardOpenOption.CREATE,StandardOpenOption.WRITE,StandardOpenOption.TRUNCATE_EXISTING)
        ){
            ByteBuffer buffer = ByteBuffer.allocate(8192);
            int bytesRead;

            while ((bytesRead=source.read(buffer))!=-1){
                buffer.flip();

                while (buffer.hasRemaining()){
                    destination.write(buffer);
                }
                buffer.clear();
            }
            System.out.println("File Copied Successfully");

        }catch (IOException e){
            System.err.println("Unable to Copy " + e.getMessage());
        }
    }
}
