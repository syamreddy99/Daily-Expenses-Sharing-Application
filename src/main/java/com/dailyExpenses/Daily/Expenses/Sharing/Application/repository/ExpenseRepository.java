package com.dailyExpenses.Daily.Expenses.Sharing.Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailyExpenses.Daily.Expenses.Sharing.Application.Entities.Expense;

/**
 * Repository interface for managing Expense entities in the Daily Expenses Sharing Application.
 * This interface extends JpaRepository, providing CRUD operations and additional query methods for Expense entities.
 */
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
    /**
     * Retrieves a list of Expense entities associated with a specific user.
     *
     * @param userId the ID of the user for whom to find expenses
     * @return a list of Expense entities related to the specified user
     */
    List<Expense> findByUserId(Long userId);
}
