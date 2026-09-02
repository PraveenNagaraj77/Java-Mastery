package com.javamastery.collections.arrayList;

import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("Spring Boot");
        skills.add("React");
        skills.add("Node.js");
        skills.add("SQL");
        skills.add("Docker");

        for (String skill : skills){
            System.out.println(skill);
        }
        System.out.println(skills);

        System.out.println(skills.get(0));

        skills.set(3,"Angular");
        System.out.println(skills);

        skills.remove(5);

        System.out.println(skills.contains("Java"));

        System.out.println(skills.size());




    }
}
