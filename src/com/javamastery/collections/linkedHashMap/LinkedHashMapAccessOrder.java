package com.javamastery.collections.linkedHashMap;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapAccessOrder {
    public static void main(String[] args) {
        LinkedHashMap<Integer,String> map = new LinkedHashMap<>(16,0.75f,true);

        map.put(101, "Praveen");
        map.put(102, "Rahul");
        map.put(103, "Anjali");

        map.get(101);

        for (Map.Entry<Integer,String> entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

