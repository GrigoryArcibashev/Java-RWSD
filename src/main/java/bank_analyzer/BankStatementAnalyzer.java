package bank_analyzer;

import java.util.List;

public class BankStatementAnalyzer {
    public SummaryStatistics analyze(final List<BankTransaction> bankTransactions) {
        return new BankStatementProcessor(bankTransactions).summarizeTransactions();
    }
}
