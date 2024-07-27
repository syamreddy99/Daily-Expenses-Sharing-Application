package com.dailyExpenses.Daily.Expenses.Sharing.Application.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailyExpenses.Daily.Expenses.Sharing.Application.dto.ExpenseDTO;
import com.dailyExpenses.Daily.Expenses.Sharing.Application.service.ExpenseService;

/**
 * Controller for managing expense-related operations in the Daily Expenses Sharing Application.
 * Provides endpoints for adding expenses, retrieving user expenses, getting all expenses, 
 * and downloading balance sheets.
 */
@RestController

//http://localhost:9982/api/expenses
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    /**
     * Endpoint to add a new expense.
     *
     * @param expenseDTO The DTO containing expense details to be added.
     * @return A ResponseEntity containing the created ExpenseDTO and HTTP status 201 (Created).
     */
    //http://localhost:9982/api/expenses
    @PostMapping
    public ResponseEntity<ExpenseDTO> addExpense(@RequestBody ExpenseDTO expenseDTO) {
        ExpenseDTO createdExpense = expenseService.addExpense(expenseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
    }

    /**
     * Endpoint to retrieve all expenses for a specific user.
     *
     * @param userId The ID of the user whose expenses are to be retrieved.
     * @return A ResponseEntity containing a list of ExpenseDTOs for the user and HTTP status 200 (OK).
     */
    //http://localhost:9982/api/expenses/users/1
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExpenseDTO>> getUserExpenses(@PathVariable Long userId) {
        List<ExpenseDTO> expenses = expenseService.getUserExpenses(userId);
        return ResponseEntity.ok(expenses);
    }

    /**
     * Endpoint to retrieve all expenses across the application.
     *
     * @return A ResponseEntity containing a list of all ExpenseDTOs and HTTP status 200 (OK).
     */
    
       //http://localhost:9982/api/expenses/overall
    @GetMapping("/overall")
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        List<ExpenseDTO> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    /**
     * Endpoint to download a balance sheet for a specific user or all users.
     *
     * @param userIdOrAll The ID of the user or the keyword "overall" to download all users' balance sheets.
     * @return A ResponseEntity containing the balance sheet as a Resource and HTTP status 200 (OK).
     * @throws IOException If an I/O error occurs during the download process.
     */
    //http://localhost:9982/api/expenses/download/1 or all
    @GetMapping("/download/{userIdOrAll}")
    public ResponseEntity<Resource> downloadBalanceSheet(@PathVariable String userIdOrAll) throws IOException {
        Resource file;
        if (userIdOrAll.equalsIgnoreCase("overall")) {
            file = expenseService.downloadOverallBalanceSheet();
        } else {
            Long userId = Long.valueOf(userIdOrAll);
            file = expenseService.downloadBalanceSheet(userId);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=balance_sheet.csv")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
}
