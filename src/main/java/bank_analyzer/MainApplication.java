package bank_analyzer;

import bank_analyzer.converters.HtmlConverter;
import bank_analyzer.parsers.BankStatementCSVParser;
import bank_analyzer.readers.FileReader;
import bank_analyzer.writers.FileWriter;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MainApplication {
    private static final String RESOURCES = "src/main/resources/";

    public static void main(final String[] args) throws Exception {
        final Path pathToImport = Paths.get(RESOURCES + "bank-data-simple.csv");
        final Importer importer = new Importer(new FileReader(pathToImport), new BankStatementCSVParser());
        final Path pathToExport = Paths.get(RESOURCES + "index.html");
        final Exporter exporter = new Exporter(new FileWriter(pathToExport), new HtmlConverter());
        exporter.export(new BankStatementAnalyzer().analyze(importer.importBankTransactions()));
    }
}
