package bank_analyzer;

import bank_analyzer.exporters.Exporter;
import bank_analyzer.exporters.HtmlExporter;
import bank_analyzer.importers.FileImporter;
import bank_analyzer.importers.Importer;
import bank_analyzer.parsers.BankStatementCSVParser;
import bank_analyzer.parsers.BankStatementParser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainApplication {
    private static final String RESOURCES = "src/main/resources/";

    public static void main(final String[] args) throws Exception {
        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final BankStatementParser parser = new BankStatementCSVParser();
        final Importer importer = new FileImporter(path, parser);
        final Exporter exporter = new HtmlExporter();
        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        String report = bankStatementAnalyzer.analyze(importer, exporter);
        Files.write(Paths.get(RESOURCES + "index.html"), report.getBytes());
    }
}
