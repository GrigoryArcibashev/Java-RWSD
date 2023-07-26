package com.iteratrlearning.shu_book.chapter_03;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class BankStatementProcessorTest {
    private final com.iteratrlearning.shu_book.chapter_02.BankTransaction[] bankTransactions = {
            new com.iteratrlearning.shu_book.chapter_02.BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Test"),
            new com.iteratrlearning.shu_book.chapter_02.BankTransaction(LocalDate.of(2018, Month.JANUARY, 30), -200, "LEAST"),
            new com.iteratrlearning.shu_book.chapter_02.BankTransaction(LocalDate.of(2019, Month.JANUARY, 30), -100, "Test"),
            new com.iteratrlearning.shu_book.chapter_02.BankTransaction(LocalDate.of(2020, Month.JANUARY, 30), 500, "MOST"),
            new com.iteratrlearning.shu_book.chapter_02.BankTransaction(LocalDate.of(2021, Month.JANUARY, 30), 200, "Test"),
            new com.iteratrlearning.shu_book.chapter_02.BankTransaction(LocalDate.of(2022, Month.JANUARY, 30), 50, "Test"),
            new com.iteratrlearning.shu_book.chapter_02.BankTransaction(LocalDate.of(2023, Month.JANUARY, 30), -50, "Test"),
    };
    private final com.iteratrlearning.shu_book.chapter_02.BankStatementProcessor bankStatementProcessor = new com.iteratrlearning.shu_book.chapter_02.BankStatementProcessor(List.of(bankTransactions));

    @Test
    public void getLeastExpensiveBankTransaction_simpleTest() {
        LocalDate startPeriod = LocalDate.of(2018, Month.JANUARY, 1);
        LocalDate endPeriod = LocalDate.of(2023, Month.JANUARY, 1);
        com.iteratrlearning.shu_book.chapter_02.BankTransaction expectedLeastExpensiveBankTransaction =
                new com.iteratrlearning.shu_book.chapter_02.BankTransaction(LocalDate.of(2018, Month.JANUARY, 30), -200, "LEAST");

        Optional<com.iteratrlearning.shu_book.chapter_02.BankTransaction> actualLeastExpensiveBankTransaction =
                bankStatementProcessor.getLeastExpensiveBankTransaction(startPeriod, endPeriod);
        bankStatementProcessor.getMostExpensiveBankTransaction(startPeriod, endPeriod);
        Assert.assertTrue(actualLeastExpensiveBankTransaction.isPresent());
        Assert.assertEquals(expectedLeastExpensiveBankTransaction, actualLeastExpensiveBankTransaction.get());
    }

    @Test
    public void getMostExpensiveBankTransaction_simpleTest() {
        LocalDate startPeriod = LocalDate.of(2018, Month.JANUARY, 1);
        LocalDate endPeriod = LocalDate.of(2023, Month.JANUARY, 1);
        com.iteratrlearning.shu_book.chapter_02.BankTransaction expectedMostExpensiveBankTransaction =
                new com.iteratrlearning.shu_book.chapter_02.BankTransaction(LocalDate.of(2020, Month.JANUARY, 30), 500, "MOST");

        Optional<com.iteratrlearning.shu_book.chapter_02.BankTransaction> actualMostExpensiveBankTransaction =
                bankStatementProcessor.getMostExpensiveBankTransaction(startPeriod, endPeriod);
        Assert.assertTrue(actualMostExpensiveBankTransaction.isPresent());
        Assert.assertEquals(expectedMostExpensiveBankTransaction, actualMostExpensiveBankTransaction.get());
    }

    @Test
    public void getLeastExpensiveBankTransaction_shouldNotGetAnything() {
        LocalDate startPeriod = LocalDate.of(2010, Month.JANUARY, 1);
        LocalDate endPeriod = LocalDate.of(2014, Month.JANUARY, 1);

        Optional<com.iteratrlearning.shu_book.chapter_02.BankTransaction> actualLeastExpensiveBankTransaction =
                bankStatementProcessor.getLeastExpensiveBankTransaction(startPeriod, endPeriod);
        Assert.assertFalse(actualLeastExpensiveBankTransaction.isPresent());
    }

    @Test
    public void getMostExpensiveBankTransaction_shouldNotGetAnything() {
        LocalDate startPeriod = LocalDate.of(2010, Month.JANUARY, 1);
        LocalDate endPeriod = LocalDate.of(2014, Month.JANUARY, 1);

        Optional<com.iteratrlearning.shu_book.chapter_02.BankTransaction> actualMostExpensiveBankTransaction =
                bankStatementProcessor.getMostExpensiveBankTransaction(startPeriod, endPeriod);
        Assert.assertFalse(actualMostExpensiveBankTransaction.isPresent());
    }

    @Test
    public void shouldFilterTransactionsInFebruary() {

        final BankTransaction februarySalary
                = new BankTransaction(LocalDate.of(2019, Month.FEBRUARY, 14), 2_000, "Salary");

        final BankTransaction marchRoyalties
                = new BankTransaction(LocalDate.of(2019, Month.MARCH, 20), 500, "Royalties");

        final List<BankTransaction> bankTransactions
                = List.of(februarySalary,
                marchRoyalties);

        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
        final List<BankTransaction> transactions
                = bankStatementProcessor.findTransactions(new BankTransactionIsInFebruaryAndExpensive());

        assertThat(transactions, contains(februarySalary));
        assertThat(transactions, hasSize(1));
    }


    class BankTransactionIsInFebruaryAndExpensive implements BankTransactionFilter {
        @Override
        public boolean test(final BankTransaction bankTransaction) {
            return bankTransaction.getDate().getMonth() == Month.FEBRUARY && bankTransaction.getAmount() >= 1_000;
        }
    }
}