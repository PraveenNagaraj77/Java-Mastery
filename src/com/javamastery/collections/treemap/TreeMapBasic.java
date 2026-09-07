package com.javamastery.collections.treemap;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapBasic {
    public static void main(String[] args) {
        TreeMap<Integer,String> map = new TreeMap<>();
        map.put(105,"Vijay");
        map.put(101,"Praveen");
        map.put(104,"Anjali");
        map.put(102,"Rahul");
        map.put(10, "A");
        map.put(20, "B");
        map.put(30, "C");
        map.put(40, "D");
        map.put(50, "E");

        for (Map.Entry<Integer,String> no : map.entrySet()){
            System.out.println(no.getKey() + " :  " + no.getValue());
        }

        System.out.println(map.firstKey());
        System.out.println(map.lastKey());
        System.out.println();
        System.out.println(map.lowerKey(104));
        System.out.println(map.higherKey(102));

        System.out.println(map.floorKey(25));
        System.out.println(map.ceilingKey(25));

        System.out.println(map.subMap(20, 40));
        System.out.println(map.headMap(30));
        System.out.println(map.tailMap(30));

        System.out.println(map.descendingMap());
        System.out.println(map.descendingKeySet());
    }



}
