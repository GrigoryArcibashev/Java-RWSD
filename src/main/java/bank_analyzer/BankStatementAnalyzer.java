package bank_analyzer;

import bank_analyzer.exporters.Exporter;
import bank_analyzer.parsers.BankStatementParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "src/main/resources/";

    public String analyze(final String fileName,
                          final BankStatementParser bankStatementParser,
                          final Exporter exporter) throws IOException {
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);
        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
        final SummaryStatistics summaryStatistics = bankStatementProcessor.summarizeTransactions();
        return exporter.export(summaryStatistics);
    }
}
