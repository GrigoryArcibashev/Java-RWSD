package bank_analyzer;

import bank_analyzer.readers.FileReader;
import bank_analyzer.readers.exceptions.FileReaderException;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReaderTests {
    private static final String RESOURCES = "src/test/resources/";

    @Test
    public void shouldReadCorrectly() {
        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final FileReader fileReader = new FileReader(path);

        final String[] expected = new String[]
                {
                        "30-01-2017,-100,Deliveroo",
                        "30-01-2017,-50,Tesco",
                        "01-02-2017,6000,Salary",
                        "02-02-2017,2000,Royalties",
                        "02-02-2017,-4000,Rent",
                        "03-02-2017,3000,Tesco",
                        "05-02-2017,-30,Cinema"
                };

        try {
            final List<String> lines = fileReader.read();
            Assert.assertArrayEquals(lines.toArray(), expected);
        } catch (FileReaderException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void shouldNotRead() {
        final Path path = Paths.get("non-existent-file");
        final FileReader fileReader = new FileReader(path);

        try {
            fileReader.read();
            Assert.fail();
        } catch (FileReaderException e) {
            Assert.assertTrue(true);
        }
    }
}
