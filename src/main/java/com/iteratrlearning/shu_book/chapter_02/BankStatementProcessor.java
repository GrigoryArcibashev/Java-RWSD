package com.iteratrlearning.shu_book.chapter_02;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Date;
import java.util.List;

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

    public BankTransaction getMostExpensiveTransaction(final LocalDate startPeriod, final LocalDate endPeriod) {
        double maxAmount = Double.NEGATIVE_INFINITY;
        BankTransaction result = null;
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (dateBetween(startPeriod, endPeriod, bankTransaction.getDate())
                    && bankTransaction.getAmount() > maxAmount) {
                maxAmount = bankTransaction.getAmount();
                result = bankTransaction;
            }
        }
        return result;
    }

    public BankTransaction getLeastExpensiveTransaction(final LocalDate startPeriod, final LocalDate endPeriod) {
        double minAmount = Double.POSITIVE_INFINITY;
        BankTransaction result = null;
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (dateBetween(startPeriod, endPeriod, bankTransaction.getDate())
                    && bankTransaction.getAmount() < minAmount) {
                minAmount = bankTransaction.getAmount();
                result = bankTransaction;
            }
        }
        return result;
    }

    private static boolean dateBetween(LocalDate start, LocalDate end, LocalDate date) {
        return start.isBefore(date) && end.isAfter(date)
                || start.isEqual(date)
                || end.isEqual(date);
    }
}
