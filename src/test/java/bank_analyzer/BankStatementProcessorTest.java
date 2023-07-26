package bank_analyzer;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

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
        final List<BankTransaction> transactions = bankStatementProcessor.findTransactions(
                transaction -> transaction.getDate().getMonth() == Month.FEBRUARY && transaction.getAmount() >= 1_000);

        assertThat(transactions, contains(februarySalary));
        assertThat(transactions, hasSize(1));
    }
}