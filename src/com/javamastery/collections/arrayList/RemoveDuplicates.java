package com.javamastery.collections.arrayList;

import java.util.ArrayList;
import java.util.List;

public class RemoveDuplicates {
    public static void main(String[] args) {
        List<String> technologies = new ArrayList<>();

        technologies.add("Java");
        technologies.add("React");
        technologies.add("Java");
        technologies.add("SQL");
        technologies.add("React");
        technologies.add("Node.js");

        System.out.println(technologies);

        List<String> updatedTechnologies = new ArrayList<>();

        for (String technology : technologies){
            if(!updatedTechnologies.contains(technology)){
                updatedTechnologies.add(technology);
            }
        }
        System.out.println(updatedTechnologies);
    }
}
