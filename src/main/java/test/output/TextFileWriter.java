package test.output;

import test.RunClient;

import java.io.IOException;

public class TextFileWriter implements FileWriter {

    java.io.FileWriter outputTextFileWriter;

    private TextFileWriter() {
        super();

    }

    private volatile static TextFileWriter uniqueInstance;

    public static TextFileWriter getInstance() {
        if (uniqueInstance == null) {
            synchronized (TextFileWriter.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new TextFileWriter();
                }
            }
        }
        return uniqueInstance;
    }

    @Override
    public void write(String message) throws IOException {
        try {
            this.outputTextFileWriter = new java.io.FileWriter(RunClient.outputTextFilePath);
            outputTextFileWriter.write(message);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            outputTextFileWriter.close();
        }
    }
}
