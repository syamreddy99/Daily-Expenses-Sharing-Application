package com.dailyExpenses.Daily.Expenses.Sharing.Application.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a split of an expense among users in the Daily Expenses Sharing Application.
 * The Split class contains information about the split type, the amount each user owes, 
 * and the user and expense identifiers associated with the split.
 */
@Entity
public class Split {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SplitType splitType;

    private double amount;
    private Long userId;
    private Long expenseId;
    private Double percentage; // Represents the percentage amount for percentage splits

    // Default constructor
    public Split() {
    }

    /**
     * All-args constructor to create a Split instance with the specified parameters.
     *
     * @param id          the unique identifier for the split
     * @param splitType   the type of split (EQUAL, EXACT, PERCENTAGE)
     * @param amount      the amount assigned to this split
     * @param userId      the identifier of the user associated with this split
     * @param expenseId   the identifier of the expense associated with this split
     * @param percentage   the percentage of the total amount for percentage splits
     */
    public Split(Long id, SplitType splitType, double amount, Long userId, Long expenseId, Double percentage) {
        this.id = id;
        this.splitType = splitType;
        this.amount = amount;
        this.userId = userId;
        this.expenseId = expenseId;
        this.percentage = percentage;
    }

    // Getters and setters

    /**
     * Gets the unique identifier of the split.
     *
     * @return the id of the split
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the split.
     *
     * @param id the id to set for the split
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the type of split.
     *
     * @return the split type
     */
    public SplitType getSplitType() {
        return splitType;
    }

    /**
     * Sets the type of split.
     *
     * @param splitType the split type to set
     */
    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
    }

    /**
     * Gets the amount assigned to this split.
     *
     * @return the amount for the split
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount assigned to this split.
     *
     * @param amount the amount to set for the split
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the identifier of the user associated with this split.
     *
     * @return the userId of the user
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the identifier of the user associated with this split.
     *
     * @param userId the userId to set for the split
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets the identifier of the expense associated with this split.
     *
     * @return the expenseId of the expense
     */
    public Long getExpenseId() {
        return expenseId;
    }

    /**
     * Sets the identifier of the expense associated with this split.
     *
     * @param expenseId the expenseId to set for the split
     */
    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    /**
     * Gets the percentage for percentage splits.
     *
     * @return the percentage value
     */
    public Double getPercentage() {
        return percentage;
    }

    /**
     * Sets the percentage for percentage splits.
     *
     * @param percentage the percentage value to set
     */
    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
