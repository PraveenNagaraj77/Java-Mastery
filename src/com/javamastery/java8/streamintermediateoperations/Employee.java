package com.javamastery.java8.streamintermediateoperations;

import java.util.List;

public class Employee {
    private String name;
    private List<String> skills;

    public Employee(String name, List<String> skills) {
        this.name = name;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public List<String> getSkills() {
        return skills;
    }
}
