package bank_analyzer.readers;

import bank_analyzer.readers.exceptions.FileReaderException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReader implements Reader {
    private final Path path;

    public FileReader(Path pathToFile) {
        path = pathToFile;
    }

    @Override
    public List<String> read() throws FileReaderException {
        try {
            return readFile();
        } catch (IOException e) {
            throw new FileReaderException(e.getMessage());
        }
    }

    private List<String> readFile() throws IOException {
        return Files.readAllLines(path);
    }
}
