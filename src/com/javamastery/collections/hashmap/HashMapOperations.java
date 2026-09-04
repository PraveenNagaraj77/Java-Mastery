package com.javamastery.collections.hashmap;

import java.util.HashMap;
import java.util.Map;

public class HashMapOperations {
    public static void main(String[] args) {
        Map<Integer, String> students = new HashMap<>();
        students.put(101,"Praveen");
        students.put(102,"Rahul");
        students.put(103,"Anjali");
        students.put(104,"Vijay");


        System.out.println("No.of.Students : " +students.size());
        System.out.println("102 Exists :"+students.containsKey(102));
        System.out.println("999 Exists :"+students.containsKey(999));
        System.out.println("Student 103 :" +students.get(103));
        students.remove(104);

        System.out.println(students);


    }
}
