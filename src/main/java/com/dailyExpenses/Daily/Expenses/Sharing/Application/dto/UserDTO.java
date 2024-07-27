package com.dailyExpenses.Daily.Expenses.Sharing.Application.dto;

/**
 * Data Transfer Object (DTO) for User entity.
 * This class is used to transfer user data between the application layers.
 */
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private String mobile;

    // Default constructor
    public UserDTO() {
    }

    /**
     * All-args constructor to create a UserDTO instance with specified parameters.
     *
     * @param id     the unique identifier of the user
     * @param email  the email address of the user
     * @param name   the name of the user
     * @param mobile the mobile number of the user
     */
    public UserDTO(Long id, String email, String name, String mobile) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.mobile = mobile;
    }

    // Getters and setters

    /**
     * Gets the unique identifier of the user.
     *
     * @return the id of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id the id to set for the user
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the email address of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the email to set for the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the name to set for the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the mobile number of the user.
     *
     * @return the mobile number of the user
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the mobile number of the user.
     *
     * @param mobile the mobile number to set for the user
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
