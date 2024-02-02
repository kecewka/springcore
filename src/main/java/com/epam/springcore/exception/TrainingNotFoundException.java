package com.epam.springcore.exception;

import java.util.NoSuchElementException;

public class TrainingNotFoundException extends NoSuchElementException {
    public TrainingNotFoundException(String message) {
        super(message);
    }
}