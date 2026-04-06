package core.service.io;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWriterImpl implements Writer {
    @Override
    public void writeToFile(String data, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file " + fileName, e);
        }
    }
}
