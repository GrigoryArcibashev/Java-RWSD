package bank_analyzer.writers;

import bank_analyzer.writers.exceptions.FileWriterException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileWriter implements Writer {
    private final Path path;

    public FileWriter(Path pathToFile) {
        path = pathToFile;
    }

    @Override
    public void write(List<String> data) throws FileWriterException {
        try {
            writeToFile(data);
        } catch (IOException e) {
            throw new FileWriterException(e.getMessage());
        }
    }

    private void writeToFile(List<String> data) throws IOException {
        Files.write(path, String.join("", data).getBytes());
    }
}
