package com.example.servicestation.exception;

public class NonValidPlateNumberException extends Exception{
    public NonValidPlateNumberException(String message) {
        super(message);
    }
}
