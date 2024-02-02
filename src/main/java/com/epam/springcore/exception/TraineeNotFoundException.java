package com.epam.springcore.exception;

import java.util.NoSuchElementException;

public class TraineeNotFoundException extends NoSuchElementException {
    public TraineeNotFoundException(String message) {
        super(message);
    }
}
