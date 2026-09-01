package com.javamastery.strings;

public class StringBuilderDemo {
    public static void main(String[] args) {

        StringBuilder profile = new StringBuilder();

        //append
        profile.append("Name: Praveen");
        profile.append(", ");
        profile.append("Role: Java Developer");
        profile.append("Experience: 1.5 years");



        System.out.println(profile);

        //Insert
        profile.insert(0,"Employee -> ");

        // Replace
        profile.replace(30, 44, "Full Stack Developer");

        // Delete
        profile.delete(0, 12);

        System.out.println(profile);

        // Reverse
        StringBuilder word =
                new StringBuilder("Java");

        word.reverse();

        System.out.println(word);

        // Length
        System.out.println(
                "Length : "
                        + profile.length()
        );


    }
}
