package test.output;

import java.io.IOException;

public interface FileWriter {
    void write(String message) throws IOException;
}
