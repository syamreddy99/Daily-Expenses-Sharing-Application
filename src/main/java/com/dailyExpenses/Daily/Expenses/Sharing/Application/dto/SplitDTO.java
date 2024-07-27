package com.dailyExpenses.Daily.Expenses.Sharing.Application.dto;

import com.dailyExpenses.Daily.Expenses.Sharing.Application.Entities.SplitType;

/**
 * Data Transfer Object (DTO) for Split entity.
 * This class is used to transfer split information between the application layers.
 */
public class SplitDTO {
    private Long userId;
    private Double amount;
    private SplitType splitType;
    private Double percentage; // Include percentage field

    // Default constructor
    public SplitDTO() {
    }

    /**
     * All-args constructor to create a SplitDTO instance with specified parameters.
     *
     * @param userId    the unique identifier of the user associated with the split
     * @param amount    the amount assigned to the split
     * @param splitType the type of split (EQUAL, EXACT, PERCENTAGE)
     * @param percentage the percentage associated with the split, if applicable
     */
    public SplitDTO(Long userId, Double amount, SplitType splitType, Double percentage) {
        this.userId = userId;
        this.amount = amount;
        this.splitType = splitType;
        this.percentage = percentage;
    }

    // Getters and setters

    /**
     * Gets the unique identifier of the user associated with the split.
     *
     * @return the userId of the split
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier of the user associated with the split.
     *
     * @param userId the userId to set for the split
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets the amount assigned to the split.
     *
     * @return the amount of the split
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the amount assigned to the split.
     *
     * @param amount the amount to set for the split
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Gets the type of split.
     *
     * @return the splitType of the split
     */
    public SplitType getSplitType() {
        return splitType;
    }

    /**
     * Sets the type of split.
     *
     * @param splitType the splitType to set for the split
     */
    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
    }

    /**
     * Gets the percentage associated with the split, if applicable.
     *
     * @return the percentage of the split
     */
    public Double getPercentage() {
        return percentage;
    }

    /**
     * Sets the percentage associated with the split.
     *
     * @param percentage the percentage to set for the split
     */
    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
