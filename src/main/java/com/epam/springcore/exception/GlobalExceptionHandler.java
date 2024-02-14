package com.epam.springcore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ReturnMessage> handleException(UserNotFoundException exception) {
        ReturnMessage data = new ReturnMessage();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ReturnMessage> handleException(TraineeNotFoundException exception) {
        ReturnMessage data = new ReturnMessage();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ReturnMessageList> handleException(MethodArgumentNotValidException exception) {
        ReturnMessageList data = new ReturnMessageList();
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            System.out.println(fieldName + " " + errorMessage);
        });
        data.setInfo(errors);

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

}
