package com.dailyExpenses.Daily.Expenses.Sharing.Application.Entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Represents an expense in the Daily Expenses Sharing Application.
 * The Expense class contains details about the expense, including its description, amount,
 * date, and the user who incurred the expense.
 */
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double amount;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Default constructor
    public Expense() {
    }

    /**
     * All-args constructor to create an Expense instance with the specified parameters.
     *
     * @param id          the unique identifier for the expense
     * @param description a brief description of the expense
     * @param amount      the total amount of the expense
     * @param date        the date when the expense was incurred
     * @param user        the user who incurred the expense
     */
    public Expense(Long id, String description, Double amount, LocalDate date, User user) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.user = user;
    }

    // Getters and setters

    /**
     * Gets the unique identifier of the expense.
     *
     * @return the id of the expense
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the expense.
     *
     * @param id the id to set for the expense
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
     * @param description the description to set for the expense
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
     * @param amount the amount to set for the expense
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Gets the date when the expense was incurred.
     *
     * @return the date of the expense
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date when the expense was incurred.
     *
     * @param date the date to set for the expense
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the user who incurred the expense.
     *
     * @return the user associated with the expense
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who incurred the expense.
     *
     * @param user the user to set for the expense
     */
    public void setUser(User user) {
        this.user = user;
    }
}
