package test.output;

import test.RunClient;
import test.db.PopulateStaticDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CsvFileWriter implements FileWriter {

    PrintWriter outputCsvFileWriter;

    private CsvFileWriter() {
        super();

    }

    private volatile static CsvFileWriter uniqueInstance;

    public static CsvFileWriter getInstance() {
        if (uniqueInstance == null) {
            synchronized (CsvFileWriter.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new CsvFileWriter();
                }
            }
        }
        return uniqueInstance;
    }

    @Override
    public void write(String message){
        try {
            this.outputCsvFileWriter = new PrintWriter(new File(RunClient.outputCsvFilePath));
            outputCsvFileWriter.write(message);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        finally {
            outputCsvFileWriter.close();
        }
    }
}
