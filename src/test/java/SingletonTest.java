import org.junit.Assert;
import org.junit.Test;
import test.db.PopulateStaticDatabase;
import test.output.CsvFileWriter;
import test.output.TextFileWriter;

public class SingletonTest {
    @Test
    public void TestPopulateStaticDatabaseInstance(){

        PopulateStaticDatabase instance1 = PopulateStaticDatabase.getInstance();
        PopulateStaticDatabase instance2 = PopulateStaticDatabase.getInstance();
        //Passes
        Assert.assertSame("Two PopulateStaticDatabase objects are same", instance1, instance2);
    }

    @Test
    public void TestTextWriterInstance(){

        TextFileWriter instance1 = TextFileWriter.getInstance();
        TextFileWriter instance2 = TextFileWriter.getInstance();
        //Passes
        Assert.assertSame("Two TextFileWriter objects are same", instance1, instance2);
    }

    @Test
    public void TestCsvWriterInstance(){

        CsvFileWriter instance1 = CsvFileWriter.getInstance();
        CsvFileWriter instance2 = CsvFileWriter.getInstance();
        //Passes
        Assert.assertSame("Two CsvFileWriter objects are same", instance1, instance2);
    }


}
