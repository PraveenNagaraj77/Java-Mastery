package com.javamastery.controlflow;

public class ConditionalStatementsDemo {
    public static void main(String[] args) {
        int age = 26;

        if(age>=18){
            System.out.println("You are eligible to vote ");
        }

        //IFELSE

        int number = 15;

        if(number % 2 == 0){
            System.out.println(number + "Is Even");
        }else{
            System.out.println(number + "Is Odd");
        }

        //If else Ladder
        int marks = 85; if (marks >= 90) {
            System.out.println("Grade: A+");
        } else if (marks >= 80) {
            System.out.println("Grade: A");
        } else if (marks >= 70) {
            System.out.println("Grade: B");
        } else if (marks >= 60)
        { System.out.println("Grade: C");
        } else {
            System.out.println("Grade: F");
        }

//        Nested IF
        boolean isEmployee = true;
        boolean isActive = true;

        if(isEmployee){
            if(isActive){
                System.out.println("Employee is Active");
            }else{
                System.out.println("Employee is not Active");
            }
        }else{
            System.out.println("Not an Employee");
        }

        int experience = 3;
        boolean javaKnowledge = true;
        if (experience >= 2 && javaKnowledge) {
            System.out.println("Eligible for Java developer role");
        } else {
            System.out.println("Not eligible");
        }


    }
}
