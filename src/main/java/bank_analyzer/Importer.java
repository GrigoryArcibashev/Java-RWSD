package bank_analyzer;

import bank_analyzer.parsers.BankStatementParser;
import bank_analyzer.readers.Reader;
import bank_analyzer.readers.exceptions.ReaderException;

import java.util.List;

public class Importer {
    private final Reader reader;
    private final BankStatementParser parser;

    public Importer(Reader reader, BankStatementParser parser) {
        this.reader = reader;
        this.parser = parser;
    }

    public List<BankTransaction> importBankTransactions() throws ReaderException {
        return parser.parseLinesFrom(reader.read());
    }
}
