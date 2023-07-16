package com.iteratrlearning.shu_book.chapter_02;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        double total = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public double calculateTotalInMonth(final Month month) {
        double total = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public double calculateTotalForCategory(final String category) {
        double total = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDescription().equals(category)) {
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public Optional<BankTransaction> getMostExpensiveBankTransaction(
            final LocalDate startPeriod,
            final LocalDate endPeriod) {
        return bankTransactions
                .stream()
                .filter(bankTransaction -> dateBetween(startPeriod, endPeriod, bankTransaction.getDate()))
                .max(Comparator.comparingDouble(BankTransaction::getAmount));
    }

    public Optional<BankTransaction> getLeastExpensiveBankTransaction(
            final LocalDate startPeriod,
            final LocalDate endPeriod) {
        return bankTransactions
                .stream()
                .filter(bankTransaction -> dateBetween(startPeriod, endPeriod, bankTransaction.getDate()))
                .min(Comparator.comparingDouble(BankTransaction::getAmount));
    }

    private static boolean dateBetween(LocalDate start, LocalDate end, LocalDate date) {
        return start.isBefore(date) && end.isAfter(date)
                || start.isEqual(date)
                || end.isEqual(date);
    }
}
