import org.junit.Test;
import test.output.CsvFileWriter;
import test.output.FileWriter;
import test.output.FileWriterFactory;
import test.output.TextFileWriter;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class FactoryTest {
    @Test
    public void testCsvFileWriter(){
        FileWriterFactory fileWriterFactory = new FileWriterFactory();
        FileWriter csvWriter = fileWriterFactory.getFileWriter("CSV");
        assertTrue(csvWriter instanceof CsvFileWriter);
        assertFalse(csvWriter instanceof TextFileWriter);
    }

    @Test
    public void testTextFileWriter(){
        FileWriterFactory fileWriterFactory = new FileWriterFactory();
        FileWriter textWriter = fileWriterFactory.getFileWriter("TEXT");
        assertFalse(textWriter instanceof CsvFileWriter);
        assertTrue(textWriter instanceof TextFileWriter);
    }
}
