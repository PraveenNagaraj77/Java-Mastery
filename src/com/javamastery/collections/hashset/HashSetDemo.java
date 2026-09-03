package com.javamastery.collections.hashset;

import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {
    public static void main(String[] args) {
        Set<String> technologies = new HashSet<>();
        technologies.add("Java");
        technologies.add("Spring Boot");
        technologies.add("React");
        technologies.add("Java");
        technologies.add("SQL");
        technologies.add("React");
        technologies.add("Node.js");
        technologies.add("Java");

        System.out.println(technologies);

        System.out.println(technologies.contains("Java"));
        System.out.println(technologies.contains("Python"));
        technologies.remove("React");
        System.out.println(technologies);

        for (String skill : technologies){
            System.out.println(skill);
        }

    }
}
