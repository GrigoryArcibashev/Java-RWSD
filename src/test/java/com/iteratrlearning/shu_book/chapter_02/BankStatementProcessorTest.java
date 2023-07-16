package com.iteratrlearning.shu_book.chapter_02;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

public class BankStatementProcessorTest {
    @Test
    public void simpleTest() {
        BankTransaction[] bankTransactions = {
                new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Test"),
                new BankTransaction(LocalDate.of(2018, Month.JANUARY, 30), -200, "Lest"),
                new BankTransaction(LocalDate.of(2019, Month.JANUARY, 30), -100, "Test"),
                new BankTransaction(LocalDate.of(2020, Month.JANUARY, 30), 500, "Most"),
                new BankTransaction(LocalDate.of(2021, Month.JANUARY, 30), 200, "Test"),
                new BankTransaction(LocalDate.of(2022, Month.JANUARY, 30), 50, "Test"),
                new BankTransaction(LocalDate.of(2023, Month.JANUARY, 30), -50, "Test"),
        };
        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(List.of(bankTransactions));
        LocalDate startPeriod = LocalDate.of(2018, Month.JANUARY, 1);
        LocalDate endPeriod = LocalDate.of(2023, Month.JANUARY, 1);

        Optional<BankTransaction> leastExpensiveBankTransaction =
                bankStatementProcessor.getLeastExpensiveBankTransaction(startPeriod, endPeriod);
        Optional<BankTransaction> mostExpensiveBankTransaction =
                bankStatementProcessor.getMostExpensiveBankTransaction(startPeriod, endPeriod);

    }
}
