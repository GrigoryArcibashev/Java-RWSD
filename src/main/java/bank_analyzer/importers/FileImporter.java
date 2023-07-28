package bank_analyzer.importers;

import bank_analyzer.BankTransaction;
import bank_analyzer.importers.exceptions.FileImporterException;
import bank_analyzer.parsers.BankStatementParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileImporter implements Importer {
    private final Path path;
    private final BankStatementParser parser;

    public FileImporter(Path pathToFile, BankStatementParser bankStatementParser) {
        path = pathToFile;
        parser = bankStatementParser;
    }

    public List<BankTransaction> importData() throws FileImporterException {
        try {
            return parser.parseLinesFrom(readFile());
        } catch (IOException e) {
            throw new FileImporterException(e.getMessage());
        }
    }

    private List<String> readFile() throws IOException {
        return Files.readAllLines(path);
    }
}
