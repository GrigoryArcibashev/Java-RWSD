package bank_analyzer.importers;

import bank_analyzer.BankTransaction;
import bank_analyzer.importers.exceptions.ImporterException;

import java.util.List;

public interface Importer {
    List<BankTransaction> importData() throws ImporterException;
}
