package com.dailyExpenses.Daily.Expenses.Sharing.Application.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the Daily Expenses Sharing Application.
 * This class is responsible for handling exceptions thrown by the application
 * and returning appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ResourceNotFoundException thrown by the application.
     *
     * @param ex the ResourceNotFoundException instance
     * @return a ResponseEntity containing the error message and HTTP status NOT_FOUND (404)
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Add handlers for other exceptions if needed
}
