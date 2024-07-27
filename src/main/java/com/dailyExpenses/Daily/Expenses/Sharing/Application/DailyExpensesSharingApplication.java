package com.dailyExpenses.Daily.Expenses.Sharing.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Daily Expenses Sharing Application.
 * This class is responsible for launching the Spring Boot application.
 */
@SpringBootApplication
public class DailyExpensesSharingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyExpensesSharingApplication.class, args);
        System.out.println("Entering the Application...");
    }
}
