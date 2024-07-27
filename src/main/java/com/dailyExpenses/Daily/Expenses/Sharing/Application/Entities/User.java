package com.dailyExpenses.Daily.Expenses.Sharing.Application.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a user in the Daily Expenses Sharing Application.
 * This class maps to the "User" table in the database.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String mobile;

    // Default constructor
    /**
     * Default constructor for the User class.
     * This constructor is required by JPA for entity creation.
     */
    public User() {
    }

    // All-args constructor
    /**
     * Constructs a new User with the specified parameters.
     *
     * @param id the unique identifier for the user
     * @param email the email address of the user
     * @param name the name of the user
     * @param mobile the mobile number of the user
     */
    public User(Long id, String email, String name, String mobile) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.mobile = mobile;
    }

    // Getters and setters
    /**
     * Gets the unique identifier of the user.
     *
     * @return the user's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id the unique identifier to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the email address of the user.
     *
     * @return the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the name of the user.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the mobile number of the user.
     *
     * @return the user's mobile number
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the mobile number of the user.
     *
     * @param mobile the mobile number to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
