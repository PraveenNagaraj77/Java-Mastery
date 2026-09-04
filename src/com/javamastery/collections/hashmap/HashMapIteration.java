package com.javamastery.collections.hashmap;

import java.util.HashMap;
import java.util.Map;

public class HashMapIteration {
    public static void main(String[] args) {
        Map<Integer,String> students = new HashMap<>();
        students.put(101,"Praveen");
        students.putIfAbsent(101, "Nagaraj");
        students.put(102,"Rahul");
        students.put(103,"Anjali");
        students.put(104,"Vijay");

        for (Map.Entry<Integer,String> entry : students.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

    }
}
