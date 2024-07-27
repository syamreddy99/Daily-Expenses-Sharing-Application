package com.dailyExpenses.Daily.Expenses.Sharing.Application.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.dailyExpenses.Daily.Expenses.Sharing.Application.Entities.Expense;
import com.dailyExpenses.Daily.Expenses.Sharing.Application.Entities.Split;
import com.dailyExpenses.Daily.Expenses.Sharing.Application.Entities.SplitType;
import com.dailyExpenses.Daily.Expenses.Sharing.Application.Entities.User;
import com.dailyExpenses.Daily.Expenses.Sharing.Application.Exception.ResourceNotFoundException;
import com.dailyExpenses.Daily.Expenses.Sharing.Application.dto.ExpenseDTO;
import com.dailyExpenses.Daily.Expenses.Sharing.Application.dto.SplitDTO;
import com.dailyExpenses.Daily.Expenses.Sharing.Application.repository.ExpenseRepository;
import com.dailyExpenses.Daily.Expenses.Sharing.Application.repository.SplitRepository;
import com.dailyExpenses.Daily.Expenses.Sharing.Application.repository.UserRepository;

/**
 * Service class for managing expenses in the Daily Expenses Sharing Application.
 * This class handles the business logic for adding, retrieving, and downloading
 * expense data along with their splits.
 */
@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SplitRepository splitRepository;

    /**
     * Adds a new expense along with its splits.
     *
     * @param expenseDTO The Data Transfer Object containing expense information and splits.
     * @return The created ExpenseDTO containing the saved expense information.
     * @throws ResourceNotFoundException if the user associated with the expense does not exist.
     * @throws IllegalArgumentException if the total percentage of percentage splits does not add up to 100.
     */
    public ExpenseDTO addExpense(ExpenseDTO expenseDTO) {
        User user = userRepository.findById(expenseDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Expense expense = new Expense();
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());
        expense.setUser(user);
        Expense savedExpense = expenseRepository.save(expense);

        List<Split> splits = new ArrayList<>();
        double totalAmount = expenseDTO.getAmount();
        double totalPercentage = 0;

        for (SplitDTO splitDTO : expenseDTO.getSplits()) {
            Split split = new Split();
            split.setExpenseId(savedExpense.getId());
            split.setUserId(splitDTO.getUserId());
            split.setSplitType(splitDTO.getSplitType());

            if (splitDTO.getSplitType() == SplitType.EXACT) {
                split.setAmount(splitDTO.getAmount());
                split.setPercentage(null); // Exact splits do not use percentage
            } else if (splitDTO.getSplitType() == SplitType.PERCENTAGE) {
                split.setAmount(totalAmount * (splitDTO.getPercentage() / 100));
                split.setPercentage(splitDTO.getPercentage());
                totalPercentage += splitDTO.getPercentage();
            } else if (splitDTO.getSplitType() == SplitType.EQUAL) {
                split.setAmount(totalAmount / expenseDTO.getSplits().size());
                split.setPercentage(null); // Equal splits do not use percentage
            }

            splits.add(split);
        }

        // Validate percentages add up to 100
        if (totalPercentage != 100 && !splits.isEmpty() && splits.get(0).getSplitType() == SplitType.PERCENTAGE) {
            throw new IllegalArgumentException("Total percentage does not add up to 100");
        }

        splitRepository.saveAll(splits);
        return convertToDTO(savedExpense, splits);
    }

    /**
     * Retrieves all expenses for a specific user.
     *
     * @param userId The ID of the user whose expenses are to be retrieved.
     * @return A list of ExpenseDTO containing the user's expenses.
     */
    public List<ExpenseDTO> getUserExpenses(Long userId) {
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        return expenses.stream()
                .map(expense -> convertToDTO(expense, splitRepository.findByExpenseId(expense.getId())))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all expenses in the application.
     *
     * @return A list of ExpenseDTO containing all expenses.
     */
    public List<ExpenseDTO> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream()
                .map(expense -> convertToDTO(expense, splitRepository.findByExpenseId(expense.getId())))
                .collect(Collectors.toList());
    }

    /**
     * Converts an Expense entity and its splits to an ExpenseDTO.
     *
     * @param expense The Expense entity to be converted.
     * @param splits  The list of splits associated with the expense.
     * @return The converted ExpenseDTO containing expense and split information.
     */
    private ExpenseDTO convertToDTO(Expense expense, List<Split> splits) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(expense.getId());
        dto.setDescription(expense.getDescription());
        dto.setAmount(expense.getAmount());
        dto.setDate(expense.getDate());
        dto.setUserId(expense.getUser().getId());

        List<SplitDTO> splitDTOs = splits.stream()
                .map(split -> {
                    SplitDTO splitDTO = new SplitDTO();
                    splitDTO.setUserId(split.getUserId());
                    splitDTO.setAmount(split.getAmount());
                    splitDTO.setSplitType(split.getSplitType());
                    splitDTO.setPercentage(split.getPercentage()); // Ensure percentage is set
                    return splitDTO;
                }).collect(Collectors.toList());
        dto.setSplits(splitDTOs);

        return dto;
    }

    /**
     * Downloads the balance sheet for a specific user in CSV format.
     *
     * @param userId The ID of the user whose balance sheet is to be downloaded.
     * @return A Resource containing the CSV file data.
     * @throws ResourceNotFoundException if the user does not exist.
     * @throws IOException if there is an error during file creation.
     */
    public Resource downloadBalanceSheet(Long userId) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Expense> expenses = expenseRepository.findByUserId(userId);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(
                new PrintWriter(out, true, StandardCharsets.UTF_8),
                CSVFormat.DEFAULT.withHeader("Description", "Amount", "Date", "User Id", "Split Amount", "Split Type", "Percentage"));

        for (Expense expense : expenses) {
            List<Split> splits = splitRepository.findByExpenseId(expense.getId());
            for (Split split : splits) {
                csvPrinter.printRecord(
                        expense.getDescription(),
                        expense.getAmount(),
                        expense.getDate(),
                        expense.getUser().getId(),
                        split.getAmount(),
                        split.getSplitType(),
                        split.getPercentage()
                );
            }
        }

        csvPrinter.flush();
        byte[] data = out.toByteArray();
        return new ByteArrayResource(data);
    }

    /**
     * Downloads the overall balance sheet for all users in CSV format.
     *
     * @return A Resource containing the CSV file data for all users.
     * @throws IOException if there is an error during file creation.
     */
    public Resource downloadOverallBalanceSheet() throws IOException {
        List<Expense> expenses = expenseRepository.findAll();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(
                new PrintWriter(out, true, StandardCharsets.UTF_8),
                CSVFormat.DEFAULT.withHeader("Description", "Amount", "Date", "User Id", "Split Amount", "Split Type", "Percentage"));

        for (Expense expense : expenses) {
            List<Split> splits = splitRepository.findByExpenseId(expense.getId());
            for (Split split : splits) {
                csvPrinter.printRecord(
                        expense.getDescription(),
                        expense.getAmount(),
                        expense.getDate(),
                        expense.getUser().getId(),
                        split.getAmount(),
                        split.getSplitType(),
                        split.getPercentage()
                );
            }
        }

        csvPrinter.flush();
        byte[] data = out.toByteArray();
        return new ByteArrayResource(data);
    }
}
