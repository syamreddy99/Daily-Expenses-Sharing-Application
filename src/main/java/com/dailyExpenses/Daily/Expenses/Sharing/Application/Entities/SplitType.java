package com.dailyExpenses.Daily.Expenses.Sharing.Application.Entities;

/**
 * Enum representing the different types of expense splits in the Daily Expenses Sharing Application.
 * The split types define how the total amount of an expense is distributed among users.
 */
public enum SplitType {
    /**
     * Indicates that the total amount is split equally among all users.
     */
    EQUAL,

    /**
     * Indicates that each user pays a specific exact amount defined for them.
     */
    EXACT,

    /**
     * Indicates that each user pays a percentage of the total amount.
     */
    PERCENTAGE
}
