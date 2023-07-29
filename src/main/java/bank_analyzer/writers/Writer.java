package bank_analyzer.writers;

import bank_analyzer.writers.exceptions.WriterException;

import java.util.List;

public interface Writer {
    void write(List<String> data) throws WriterException;
}
