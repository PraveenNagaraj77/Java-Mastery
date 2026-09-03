package com.javamastery.collections.linkedHashSet;

import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicatesWhilePreservingOrder {
    public static void main(String[] args) {
        String[] technologies = {
                "Java",
                "React",
                "Java",
                "Spring Boot",
                "React",
                "SQL",
                "Java",
                "Node.js"
        };

        Set<String> technology = new LinkedHashSet<>();
        for (String tech : technologies){
            technology.add(tech);
        }
        System.out.println(technology);
    }
}
