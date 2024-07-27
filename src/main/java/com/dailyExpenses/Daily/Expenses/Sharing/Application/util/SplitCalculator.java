package com.dailyExpenses.Daily.Expenses.Sharing.Application.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dailyExpenses.Daily.Expenses.Sharing.Application.Entities.SplitType;
import com.dailyExpenses.Daily.Expenses.Sharing.Application.dto.SplitDTO;

/**
 * Utility class for calculating splits for expenses among users.
 * This class provides methods to calculate equal, exact, and percentage-based splits.
 */
public class SplitCalculator {

    /**
     * Calculates equal splits for a given total amount among specified user IDs.
     *
     * @param totalAmount The total amount to be split.
     * @param userIds A list of user IDs among whom the total amount will be split.
     * @return A list of SplitDTOs representing the equal splits for each user.
     */
    public static List<SplitDTO> calculateEqualSplits(Double totalAmount, List<Long> userIds) {
        Double splitAmount = totalAmount / userIds.size();
        return userIds.stream()
                .map(userId -> new SplitDTO(userId, splitAmount, SplitType.EQUAL, null))
                .collect(Collectors.toList());
    }

    /**
     * Calculates exact splits for a list of specified amounts among specified user IDs.
     *
     * @param amounts A list of exact amounts for each user.
     * @param userIds A list of user IDs corresponding to the amounts.
     * @return A list of SplitDTOs representing the exact splits for each user.
     * @throws IllegalArgumentException if the size of amounts and userIds lists do not match.
     */
    public static List<SplitDTO> calculateExactSplits(List<Double> amounts, List<Long> userIds) {
        if (amounts.size() != userIds.size()) {
            throw new IllegalArgumentException("The number of amounts must match the number of user IDs.");
        }

        List<SplitDTO> splits = new ArrayList<>();
        for (int i = 0; i < userIds.size(); i++) {
            splits.add(new SplitDTO(userIds.get(i), amounts.get(i), SplitType.EXACT, null));
        }
        return splits;
    }

    /**
     * Calculates percentage-based splits for a given total amount among specified user IDs.
     *
     * @param totalAmount The total amount to be split.
     * @param percentages A list of percentages corresponding to each user.
     * @param userIds A list of user IDs among whom the total amount will be split.
     * @return A list of SplitDTOs representing the percentage splits for each user.
     * @throws IllegalArgumentException if the total of percentages does not equal 100.
     */
    public static List<SplitDTO> calculatePercentageSplits(Double totalAmount, List<Double> percentages, List<Long> userIds) {
        double totalPercentage = percentages.stream().mapToDouble(Double::doubleValue).sum();
        if (totalPercentage != 100) {
            throw new IllegalArgumentException("Total percentages must equal 100.");
        }

        List<SplitDTO> splits = new ArrayList<>();
        for (int i = 0; i < userIds.size(); i++) {
            Double splitAmount = totalAmount * (percentages.get(i) / 100);
            splits.add(new SplitDTO(userIds.get(i), splitAmount, SplitType.PERCENTAGE, percentages.get(i)));
        }
        return splits;
    }
}
