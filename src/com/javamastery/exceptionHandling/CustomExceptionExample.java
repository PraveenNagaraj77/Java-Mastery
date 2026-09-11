package com.javamastery.exceptionHandling;

public class CustomExceptionExample {

    static void withdraw(double balance,double amount){
        if(amount>balance){
            throw new InsufficientBalanceException("Insufficient balance");
        }else{
            System.out.println(" Withdraw Successfull");
        }
    }

    static void registerUser(int age) throws InvalidAgeException{
        if(age<18){
            throw new InvalidAgeException("User Must be 18 or Older");
        }
        System.out.println("Registration SuccessFull");
    }

    public static void main(String[] args) {
        try {
            withdraw(5000,7000);
        }catch (InsufficientBalanceException e){
            System.out.println(e.getMessage());
        }

        try{
            registerUser(16);
        }catch (InvalidAgeException e){
            System.out.println(e.getMessage());
        }


    }
}
