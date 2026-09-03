package com.javamastery.collections.hashset;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicates {
    public static void main(String[] args) {
        Set<String> technologies = new HashSet<>();
        technologies.add("Java");
        technologies.add("React");
        technologies.add("Java");
        technologies.add("SQL");
        technologies.add("React");
        technologies.add("Node");
        technologies.add("Java");

        System.out.println(technologies);

    }
}
