package com.javamastery.collections.hashmap;

import java.util.HashMap;
import java.util.Map;

public class HashMapGetorDefault {
    public static void main(String[] args) {
        Map<Integer,String> students = new HashMap<>();
        students.put(101,"Praveen");
        students.putIfAbsent(101, "Nagaraj");
        students.put(102,"Rahul");
        students.put(103,"Anjali");

        System.out.println(students);

        System.out.println(students.get(102));
        System.out.println(students.getOrDefault(999,"Student Not Found"));
        System.out.println(students.get(103));
        System.out.println(students.getOrDefault(500,"Student Not Found"));


    }
}
