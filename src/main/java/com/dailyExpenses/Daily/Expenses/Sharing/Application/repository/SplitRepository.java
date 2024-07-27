package com.dailyExpenses.Daily.Expenses.Sharing.Application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailyExpenses.Daily.Expenses.Sharing.Application.Entities.Split;

/**
 * Repository interface for managing Split entities in the Daily Expenses Sharing Application.
 * This interface extends JpaRepository, providing CRUD operations and additional query methods for Split entities.
 */
public interface SplitRepository extends JpaRepository<Split, Long> {
    
    /**
     * Retrieves a list of Split entities associated with a specific expense.
     *
     * @param expenseId the ID of the expense for which to find splits
     * @return a list of Split entities related to the specified expense
     */
    List<Split> findByExpenseId(Long expenseId);
}
