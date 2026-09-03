package com.javamastery.collections.hashset;

import java.util.HashSet;
import java.util.Set;

public class ElementsInOneNotInAnother {
    public static void main(String[] args) {
        Set<String> skill1 = new HashSet<>();
        skill1.add("Java");
        skill1.add("React");
        skill1.add("SQL");

        Set<String> skill2 = new HashSet<>();
        skill2.add("Java");
        skill2.add("Python");
        skill2.add("AWS");

        Set<String> skills = new HashSet<>();

        for(String skillSet : skill2){
            if(!skill1.contains(skillSet)){
                skills.add(skillSet);
            }
        }

        System.out.println("Elements in B but not in A" + skills);




    }
}
