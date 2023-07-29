package bank_analyzer.readers;

import bank_analyzer.readers.exceptions.ReaderException;

import java.util.List;

public interface Reader {
    List<String> read() throws ReaderException;
}
