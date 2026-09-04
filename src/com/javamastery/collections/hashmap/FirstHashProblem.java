package com.javamastery.collections.hashmap;

import java.util.HashMap;
import java.util.Map;

public class FirstHashProblem {
    public static void main(String[] args) {
        Map<Integer,String> technologies = new HashMap<>();
        technologies.put(1,"Java");
        technologies.put(2,"React");
        technologies.put(3,"Node.js");
        technologies.put(4,"SQL");

        System.out.println(technologies);
        System.out.println(technologies.get(3));
        technologies.put(2,"Spring Boot");
        System.out.println(technologies.containsKey(4));

        System.out.println(technologies);
    }
}
