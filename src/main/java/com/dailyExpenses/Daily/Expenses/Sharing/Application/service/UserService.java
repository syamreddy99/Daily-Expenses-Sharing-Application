package com.dailyExpenses.Daily.Expenses.Sharing.Application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailyExpenses.Daily.Expenses.Sharing.Application.Entities.User;
import com.dailyExpenses.Daily.Expenses.Sharing.Application.Exception.ResourceNotFoundException;
import com.dailyExpenses.Daily.Expenses.Sharing.Application.dto.UserDTO;
import com.dailyExpenses.Daily.Expenses.Sharing.Application.repository.UserRepository;

/**
 * Service class for managing users in the Daily Expenses Sharing Application.
 * This class handles the business logic for creating and retrieving user information.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new user based on the provided UserDTO.
     *
     * @param userDTO The Data Transfer Object containing user information to be saved.
     * @return The created UserDTO containing the saved user's information.
     */
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setMobile(userDTO.getMobile());
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to be retrieved.
     * @return The UserDTO containing the user's information.
     * @throws ResourceNotFoundException if the user with the specified ID does not exist.
     */
    public UserDTO getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return convertToDTO(user);
    }

    /**
     * Converts a User entity to its corresponding UserDTO.
     * @param user The User entity to be converted.
     * @return The converted UserDTO containing user information.
     */
    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setMobile(user.getMobile());
        return dto;
    }
}