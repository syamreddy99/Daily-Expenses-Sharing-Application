package com.dailyExpenses.Daily.Expenses.Sharing.Application.Exception;

/**
 * Exception class to be thrown when a requested resource is not found.
 * This is a custom runtime exception used in the Daily Expenses Sharing Application.
 */
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
