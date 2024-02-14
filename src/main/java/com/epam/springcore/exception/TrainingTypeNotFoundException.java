package com.epam.springcore.exception;

import java.util.NoSuchElementException;

public class TrainingTypeNotFoundException extends NoSuchElementException {
 public TrainingTypeNotFoundException(String message){
     super(message);
 }
}
