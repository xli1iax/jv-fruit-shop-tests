package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.basesyntax.reader.FileReader;
import core.basesyntax.reader.FileReaderImpl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FileReaderImplTest {
    @Test
    public void readFileIncorrectPath() {
        FileReaderImpl fileReaderImpl = new FileReaderImpl();
        assertThrows(RuntimeException.class,
                () -> fileReaderImpl.read("/invalid-path/does-not-exist/report.txt"));
    }

    @Test
    public void readValidFileSkipsHeaderAndReadsLines() throws IOException {
        FileReader fileReader = new FileReaderImpl();
        Path tempFile = Files.createTempFile("testData", ".csv");

        List<String> lines = List.of(
                "fruit,quantity",
                "Apple,10",
                "Banana,5"
        );
        Files.write(tempFile, lines);

        List<String> actual = fileReader.read(tempFile.toString());

        assertEquals(List.of("Apple,10", "Banana,5"), actual);
    }

    @Test
    public void readFileOnlyWithHeaderReturnsEmptyList() throws IOException {
        FileReader fileReader = new FileReaderImpl();
        Path tempFile = Files.createTempFile("testData", ".csv");

        Files.write(tempFile, List.of("fruit,quantity"));

        List<String> actual = fileReader.read(tempFile.toString());

        assertTrue(actual.isEmpty());
    }
}
