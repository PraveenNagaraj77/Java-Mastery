package com.javamastery.multithreading.concurrentcollections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapPractice {
    public static void main(String[] args) {
        ConcurrentHashMap<String,Integer> hashMap = new ConcurrentHashMap<>();
        hashMap.put("ORD-101",1);
        hashMap.putIfAbsent("ORD-101",5);

        for (Map.Entry<String,Integer> entry : hashMap.entrySet()){
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }


        ConcurrentHashMap<String , List<String>> hashMap1 = new ConcurrentHashMap<>();

        hashMap1.computeIfAbsent("ORD-101",key->new ArrayList<>());

        hashMap1.get("ORD-101").add("PAYMENT_Recieved");
        hashMap1.get("ORD-101").add("ORDER_CONFIRMED");


        System.out.println(hashMap1);


        ConcurrentHashMap<String,Integer> statusCounts = new ConcurrentHashMap<>();

        statusCounts.merge("COMPLETED",1,Integer::sum);
        statusCounts.merge("PENDING",1,Integer::sum);
        statusCounts.merge("COMPLETED",1,Integer::sum);
        statusCounts.merge("FAILED",1,Integer::sum);
        statusCounts.merge("COMPLETED",1,Integer::sum);
        statusCounts.merge("PENDING",1,Integer::sum);

        System.out.println(statusCounts);

    }
}
