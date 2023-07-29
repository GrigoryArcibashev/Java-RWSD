package bank_analyzer;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class BankStatementAnalyzerTests {
    @Test
    public void shouldSummarizeTransactionsCorrectly() {
        final BankTransaction[] bankTransactions = {
                new BankTransaction(LocalDate.of(2017, Month.MARCH, 1), 1000, "Test"),
                new BankTransaction(LocalDate.of(2017, Month.APRIL, 10), 2000, "Test"),
                new BankTransaction(LocalDate.of(2017, Month.APRIL, 20), -1500, "Test"),
                new BankTransaction(LocalDate.of(2017, Month.MAY, 25), -300, "Test")
        };

        final SummaryStatistics statistics = new BankStatementAnalyzer().analyze(List.of(bankTransactions));

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
}
