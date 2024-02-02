package com.epam.springcore.exception;

import java.util.NoSuchElementException;

public class TrainerNotFoundException extends NoSuchElementException {
    public TrainerNotFoundException(String message) {
        super(message);
    }
}
