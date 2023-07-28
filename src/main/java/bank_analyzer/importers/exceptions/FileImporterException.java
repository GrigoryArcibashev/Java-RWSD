package bank_analyzer.importers.exceptions;

import java.nio.file.Path;

public class FileImporterException extends ImporterException {
    public FileImporterException(String message) {
        super(message);
    }
}
