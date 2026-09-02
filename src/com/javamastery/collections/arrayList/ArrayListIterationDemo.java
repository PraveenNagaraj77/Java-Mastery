package com.javamastery.collections.arrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListIterationDemo {
    public static void main(String[] args) {

        List<String> technologies =  new ArrayList<>();

        technologies.add("Java");
        technologies.add("Spring Boot");
        technologies.add("React");
        technologies.add("Node.js");
        technologies.add("SQL");
        technologies.add("Docker");

        System.out.println("---- ForLoop--------");

        for (int i=0;i<technologies.size();i++){
            System.out.println(i + " - " + technologies.get(i));
        }

        System.out.println("----Enhanced ForLoop--------");
        for(String technology : technologies){
            System.out.println(technology);
        }

        System.out.println("----Foreach--------");

        technologies.forEach(tech-> System.out.println(tech));

        Iterator<String> iterator = technologies.iterator();
        while (iterator.hasNext()){
            String technology = iterator.next();
            if(technology.equals("Node.js")){
                iterator.remove();
            }
        }

        System.out.println(technologies);
    }
}
