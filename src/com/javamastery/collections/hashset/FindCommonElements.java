package com.javamastery.collections.hashset;

import java.util.HashSet;
import java.util.Set;

public class FindCommonElements {
    public static void main(String[] args) {

        Set<String> setA = new HashSet<>();
        setA.add("Java");
        setA.add("React");
        setA.add("SQL");
        setA.add("Node");

        Set<String> setB = new HashSet<>();
        setB.add("Python");
        setB.add("Java");
        setB.add("SQL");
        setB.add("AWS");

        Set<String> matchingElements = new HashSet<>();

        for(String technology : setA){
            if(setB.contains(technology)){
                matchingElements.add(technology);
            }

        }

        System.out.println("Common elements : " +matchingElements);

    }
}
