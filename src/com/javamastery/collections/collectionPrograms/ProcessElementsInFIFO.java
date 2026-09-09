package com.javamastery.collections.collectionPrograms;

import java.util.ArrayDeque;
import java.util.Queue;

public class ProcessElementsInFIFO {
    public static void main(String[] args) {
        String[] customers = {"Praveen", "Rahul", "Anjali", "Dhanush"};
        Queue<String> customersQueue = new ArrayDeque<>();

        for (String names : customers){
            customersQueue.offer(names);
        }

        while (!customersQueue.isEmpty()) {
            System.out.println(customersQueue.poll());
        }
    }
}
