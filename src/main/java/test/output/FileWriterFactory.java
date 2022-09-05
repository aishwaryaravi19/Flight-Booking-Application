package test.output;

public class FileWriterFactory {

    public FileWriter getFileWriter(String fileWriterType){
        if(fileWriterType == null){
            return null;
        }
        if(fileWriterType.equalsIgnoreCase("CSV")){
            return CsvFileWriter.getInstance();

        } else if(fileWriterType.equalsIgnoreCase("TEXT")){
            return TextFileWriter.getInstance();

        }

        return null;
    }
}
