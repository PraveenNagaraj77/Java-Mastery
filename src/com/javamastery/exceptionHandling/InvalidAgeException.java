package com.javamastery.exceptionHandling;

public class InvalidAgeException extends Exception  {
    public InvalidAgeException(String message){
        super(message);
    }
}
