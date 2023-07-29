package bank_analyzer;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BankStatementProcessorTests {
    @Test
    public void shouldSummarizeTransactionsCorrectly() {
        final BankTransaction[] bankTransactions = {
                new BankTransaction(LocalDate.of(2017, Month.MARCH, 1), 1000, "Test"),
                new BankTransaction(LocalDate.of(2017, Month.APRIL, 10), 2000, "Test"),
                new BankTransaction(LocalDate.of(2017, Month.APRIL, 20), -1500, "Test"),
                new BankTransaction(LocalDate.of(2017, Month.MAY, 25), -300, "Test")
        };

        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(List.of(bankTransactions));
        final SummaryStatistics statistics = bankStatementProcessor.summarizeTransactions();

        final double expectedSum = Arrays
                .stream(bankTransactions)
                .map(BankTransaction::getAmount)
                .reduce(Double::sum)
                .get();
        final double expectedAvg = expectedSum / bankTransactions.length;
        final double delta = 1E-10;
        Assert.assertEquals(statistics.getMax(), 2000, delta);
        Assert.assertEquals(statistics.getMin(), -1500, delta);
        Assert.assertEquals(statistics.getSum(), expectedSum, delta);
        Assert.assertEquals(statistics.getAverage(), expectedAvg, delta);
    }

    @Test
    public void shouldCalculateTotalInMonth() {
        final double amountMar = 1000;
        final double amountApr1 = 2000;
        final double amountApr2 = 3000;
        final double amountMay = 4000;
        final BankTransaction[] bankTransactions = {
                new BankTransaction(LocalDate.of(2017, Month.MARCH, 1), amountMar, "Test"),
                new BankTransaction(LocalDate.of(2017, Month.APRIL, 10), amountApr1, "Test"),
                new BankTransaction(LocalDate.of(2017, Month.APRIL, 20), amountApr2, "Test"),
                new BankTransaction(LocalDate.of(2017, Month.MAY, 25), amountMay, "Test")
        };

        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(List.of(bankTransactions));
        final double actual = bankStatementProcessor.calculateTotalInMonth(Month.APRIL);

        Assert.assertEquals(actual, amountApr1 + amountApr2, 1E-10);
    }

    @Test
    public void shouldSummarizeTransactionsBetweenTwoDates() {
        final double amountJan = 1000;
        final double amountApr = 2000;
        final double amountMay = 3000;
        final BankTransaction[] bankTransactions = {
                new BankTransaction(LocalDate.of(2017, Month.JANUARY, 10), amountJan, "Test"),
                new BankTransaction(LocalDate.of(2017, Month.APRIL, 20), amountApr, "Test"),
                new BankTransaction(LocalDate.of(2017, Month.MAY, 25), amountMay, "Test")
        };

        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(List.of(bankTransactions));
        final LocalDate firstDate = LocalDate.of(2017, Month.APRIL, 15);
        final LocalDate secondDate = LocalDate.of(2017, Month.MAY, 31);
        final double actual = bankStatementProcessor.summarizeTransactions((acc, transaction) ->
                transaction.getDate().isAfter(firstDate) && transaction.getDate().isBefore(secondDate)
                        ? acc + transaction.getAmount()
                        : acc);

        assertEquals(actual, amountApr + amountMay, 1E-10);
    }

    @Test
    public void shouldFilterTransactionsInMonth() {

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