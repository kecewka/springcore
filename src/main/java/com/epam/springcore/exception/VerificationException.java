package com.epam.springcore.exception;

public class VerificationException extends RuntimeException {
    public VerificationException(String message) {
        super(message);
    }
}