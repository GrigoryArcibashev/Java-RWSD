package bank_analyzer;

import bank_analyzer.exporters.Exporter;
import bank_analyzer.importers.Importer;
import bank_analyzer.importers.exceptions.ImporterException;

public class BankStatementAnalyzer {
    public String analyze(final Importer importer, final Exporter exporter) throws ImporterException {
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(importer.importData());
        return exporter.export(bankStatementProcessor.summarizeTransactions());
    }
}
