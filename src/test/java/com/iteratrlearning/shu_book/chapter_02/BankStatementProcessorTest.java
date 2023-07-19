package com.iteratrlearning.shu_book.chapter_02;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

public class BankStatementProcessorTest {
    private final BankTransaction[] bankTransactions = {
            new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Test"),
            new BankTransaction(LocalDate.of(2018, Month.JANUARY, 30), -200, "LEAST"),
            new BankTransaction(LocalDate.of(2019, Month.JANUARY, 30), -100, "Test"),
            new BankTransaction(LocalDate.of(2020, Month.JANUARY, 30), 500, "MOST"),
            new BankTransaction(LocalDate.of(2021, Month.JANUARY, 30), 200, "Test"),
            new BankTransaction(LocalDate.of(2022, Month.JANUARY, 30), 50, "Test"),
            new BankTransaction(LocalDate.of(2023, Month.JANUARY, 30), -50, "Test"),
    };
    private final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(List.of(bankTransactions));

    @Test
    public void getLeastExpensiveBankTransaction_simpleTest() {
        LocalDate startPeriod = LocalDate.of(2018, Month.JANUARY, 1);
        LocalDate endPeriod = LocalDate.of(2023, Month.JANUARY, 1);
        BankTransaction expectedLeastExpensiveBankTransaction =
                new BankTransaction(LocalDate.of(2018, Month.JANUARY, 30), -200, "LEAST");

        Optional<BankTransaction> actualLeastExpensiveBankTransaction =
                bankStatementProcessor.getLeastExpensiveBankTransaction(startPeriod, endPeriod);
        bankStatementProcessor.getMostExpensiveBankTransaction(startPeriod, endPeriod);
        Assert.assertTrue(actualLeastExpensiveBankTransaction.isPresent());
        Assert.assertEquals(expectedLeastExpensiveBankTransaction, actualLeastExpensiveBankTransaction.get());
    }

    @Test
    public void getMostExpensiveBankTransaction_simpleTest() {
        LocalDate startPeriod = LocalDate.of(2018, Month.JANUARY, 1);
        LocalDate endPeriod = LocalDate.of(2023, Month.JANUARY, 1);
        BankTransaction expectedMostExpensiveBankTransaction =
                new BankTransaction(LocalDate.of(2020, Month.JANUARY, 30), 500, "MOST");

        Optional<BankTransaction> actualMostExpensiveBankTransaction =
                bankStatementProcessor.getMostExpensiveBankTransaction(startPeriod, endPeriod);
        Assert.assertTrue(actualMostExpensiveBankTransaction.isPresent());
        Assert.assertEquals(expectedMostExpensiveBankTransaction, actualMostExpensiveBankTransaction.get());
    }

    @Test
    public void getLeastExpensiveBankTransaction_shouldNotGetAnything() {
        LocalDate startPeriod = LocalDate.of(2010, Month.JANUARY, 1);
        LocalDate endPeriod = LocalDate.of(2014, Month.JANUARY, 1);

        Optional<BankTransaction> actualLeastExpensiveBankTransaction =
                bankStatementProcessor.getLeastExpensiveBankTransaction(startPeriod, endPeriod);
        Assert.assertFalse(actualLeastExpensiveBankTransaction.isPresent());
    }

    @Test
    public void getMostExpensiveBankTransaction_shouldNotGetAnything() {
        LocalDate startPeriod = LocalDate.of(2010, Month.JANUARY, 1);
        LocalDate endPeriod = LocalDate.of(2014, Month.JANUARY, 1);

        Optional<BankTransaction> actualMostExpensiveBankTransaction =
                bankStatementProcessor.getMostExpensiveBankTransaction(startPeriod, endPeriod);
        Assert.assertFalse(actualMostExpensiveBankTransaction.isPresent());
    }
}
