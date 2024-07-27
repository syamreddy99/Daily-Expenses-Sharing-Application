package com.dailyExpenses.Daily.Expenses.Sharing.Application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Importing all necessary annotations
import com.dailyExpenses.Daily.Expenses.Sharing.Application.dto.UserDTO;
import com.dailyExpenses.Daily.Expenses.Sharing.Application.service.UserService;

/**
 * Controller for managing users in the Daily Expenses Sharing Application.
 */
@RestController
//http://localhost:9982/api/users
@RequestMapping("/api/users") // Base URI for user-related operations
public class UserController {

    @Autowired
    private UserService userService; // Injecting the UserService to handle business logic

    /**
     * Creates a new user in the system.
     *
     * @param userDTO The data transfer object containing user information.
     * @return ResponseEntity containing the created UserDTO and HTTP status 201 (Created).
     */
  // post  http://localhost:9982/api/users
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO); // Creating user through service
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser); // Returning created user
    }

    /**
     * Retrieves user information based on user ID.
     *
     * @param id The ID of the user to retrieve.
     * @return ResponseEntity containing the UserDTO and HTTP status 200 (OK) if found, or 404 (Not Found) if not found.
     */
    
  // GET http://localhost:9982/api/users/1
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO user = userService.getUser(id); // Fetching user from service
        return ResponseEntity.ok(user); // Returning user details
    }
}
