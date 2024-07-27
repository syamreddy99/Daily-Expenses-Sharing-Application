package com.dailyExpenses.Daily.Expenses.Sharing.Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailyExpenses.Daily.Expenses.Sharing.Application.Entities.User;

/**
 * Repository interface for managing User entities in the Daily Expenses Sharing Application.
 * This interface extends JpaRepository, providing CRUD operations and additional query methods for User entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository provides built-in methods for standard database operations
    // such as save, findById, findAll, deleteById, etc.
    // Custom query methods can be defined here if needed.
}
