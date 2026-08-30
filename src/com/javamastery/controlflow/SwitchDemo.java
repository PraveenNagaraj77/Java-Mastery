package com.javamastery.controlflow;

public class SwitchDemo {

    public static void main(String[] args) {

        // 1. Basic switch
        int day = 3;

        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Invalid day");
        }


        // 2. Switch with char
        char grade = 'A';

        switch (grade) {
            case 'A':
                System.out.println("Excellent");
                break;
            case 'B':
                System.out.println("Good");
                break;
            case 'C':
                System.out.println("Average");
                break;
            case 'D':
                System.out.println("Below Average");
                break;
            default:
                System.out.println("Invalid grade");
        }


        // 3. Switch with String
        String role = "ADMIN";

        switch (role) {
            case "ADMIN":
                System.out.println("Full access");
                break;
            case "MANAGER":
                System.out.println("Manager access");
                break;
            case "EMPLOYEE":
                System.out.println("Employee access");
                break;
            default:
                System.out.println("Unknown role");
        }


        // 4. Multiple cases
        int month = 1;

        switch (month) {
            case 12:
            case 1:
            case 2:
                System.out.println("Winter");
                break;

            case 3:
            case 4:
            case 5:
                System.out.println("Spring");
                break;

            case 6:
            case 7:
            case 8:
                System.out.println("Summer");
                break;

            case 9:
            case 10:
            case 11:
                System.out.println("Autumn");
                break;

            default:
                System.out.println("Invalid month");
        }


        // 5. Fall-through
        int number = 1;

        switch (number) {
            case 1:
                System.out.println("One");

            case 2:
                System.out.println("Two");

            case 3:
                System.out.println("Three");

            default:
                System.out.println("Default");
        }


        // 6. Real-world menu
        int option = 2;

        switch (option) {
            case 1:
                System.out.println("View Account");
                break;

            case 2:
                System.out.println("Transfer Money");
                break;

            case 3:
                System.out.println("Check Balance");
                break;

            case 4:
                System.out.println("Logout");
                break;

            default:
                System.out.println("Invalid option");
        }


        // 7. Banking transaction
        String transactionType = "WITHDRAW";

        double balance = 50000;
        double amount = 5000;

        switch (transactionType) {

            case "DEPOSIT":
                balance += amount;
                System.out.println("Deposit successful");
                break;

            case "WITHDRAW":
                if (amount <= balance) {
                    balance -= amount;
                    System.out.println("Withdrawal successful");
                } else {
                    System.out.println("Insufficient balance");
                }
                break;

            case "CHECK_BALANCE":
                System.out.println("Current balance: " + balance);
                break;

            default:
                System.out.println("Invalid transaction");
        }

        System.out.println("Balance: " + balance);


        // 8. Switch expression - Java 14+
        int dayNumber = 3;

        String dayName = switch (dayNumber) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            case 7 -> "Sunday";
            default -> "Invalid day";
        };

        System.out.println("Day: " + dayName);


        // 9. Multiple labels - Java 14+
        int workingDay = 6;

        String dayType = switch (workingDay) {
            case 1, 2, 3, 4, 5 -> "Weekday";
            case 6, 7 -> "Weekend";
            default -> "Invalid day";
        };

        System.out.println("Day type: " + dayType);


        // 10. Switch expression with yield
        int marks = 85;

        String result = switch (marks / 10) {
            case 10, 9 -> "A+";
            case 8 -> "A";
            case 7 -> "B";
            case 6 -> "C";
            default -> {
                if (marks >= 40) {
                    yield "D";
                } else {
                    yield "F";
                }
            }
        };

        System.out.println("Result: " + result);
    }
}