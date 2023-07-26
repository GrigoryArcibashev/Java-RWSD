package bank_analyzer;

import bank_analyzer.exporters.Exporter;
import bank_analyzer.exporters.HtmlExporter;
import bank_analyzer.parsers.BankStatementCSVParser;
import bank_analyzer.parsers.BankStatementParser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainApplication {
    public static void main(final String[] args) throws Exception {
        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();
        final Exporter exporter = new HtmlExporter();
        //TODO вынести чтение и запись данных в отдельные сущности (OCP!)
        String report = bankStatementAnalyzer.analyze("bank-data-simple.csv", bankStatementParser, exporter);
        final Path path = Paths.get("src/main/resources/index.html");
        Files.write(path, report.getBytes());
    }
}
