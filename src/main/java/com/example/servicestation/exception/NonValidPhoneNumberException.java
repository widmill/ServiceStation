package com.example.servicestation.exception;

public class NonValidPhoneNumberException extends Exception{
    public NonValidPhoneNumberException(String message) {
        super(message);
    }
}
