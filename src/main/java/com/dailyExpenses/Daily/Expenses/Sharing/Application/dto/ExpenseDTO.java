package com.dailyExpenses.Daily.Expenses.Sharing.Application.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Data Transfer Object (DTO) representing an expense in the Daily Expenses Sharing Application.
 */
public class ExpenseDTO {
    private Long id;                     // Unique identifier for the expense
    private String description;          // Description of the expense
    private Double amount;               // Total amount of the expense
    private LocalDate date;              // Date of the expense
    private Long userId;                 // ID of the user who created the expense
    private List<SplitDTO> splits;       // List of splits associated with the expense

    // Default constructor
    public ExpenseDTO() {
    }

    /**
     * All-args constructor to initialize an ExpenseDTO with provided values.
     *
     * @param id          Unique identifier for the expense
     * @param description Description of the expense
     * @param amount      Total amount of the expense
     * @param date        Date of the expense
     * @param userId      ID of the user who created the expense
     * @param splits      List of splits associated with the expense
     */
    public ExpenseDTO(Long id, String description, Double amount, LocalDate date, Long userId, List<SplitDTO> splits) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.userId = userId;
        this.splits = splits;
    }

    // Getters and setters

    /**
     * Gets the unique identifier for the expense.
     *
     * @return the ID of the expense
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the expense.
     *
     * @param id the new ID of the expense
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the description of the expense.
     *
     * @return the description of the expense
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the expense.
     *
     * @param description the new description of the expense
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the total amount of the expense.
     *
     * @return the amount of the expense
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the total amount of the expense.
     *
     * @param amount the new amount of the expense
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Gets the date of the expense.
     *
     * @return the date of the expense
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the expense.
     *
     * @param date the new date of the expense
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the ID of the user who created the expense.
     *
     * @return the user ID associated with the expense
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user who created the expense.
     *
     * @param userId the new user ID associated with the expense
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets the list of splits associated with the expense.
     *
     * @return the list of SplitDTOs representing the splits
     */
    public List<SplitDTO> getSplits() {
        return splits;
    }

    /**
     * Sets the list of splits associated with the expense.
     *
     * @param splits the new list of SplitDTOs representing the splits
     */
    public void setSplits(List<SplitDTO> splits) {
        this.splits = splits;
    }
}
