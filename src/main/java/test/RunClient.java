package test;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import javafx.util.Pair;
import test.db.PopulateStaticDatabase;
import test.output.FileWriterFactory;
import test.model.Flight;
import test.validator.*;

import java.io.*;
import java.util.HashMap;


public class RunClient {

    public HashMap<Pair<String, String>, Flight> flightHashMap;
    public static String sampleFilePath, flightsFilePath, outputCsvFilePath, outputTextFilePath;
    StringBuilder csvFileBuilder;
    StringBuilder textFileBuilder;
    String errorMessage = "Please enter correct booking details for ";
    String invalidFlightNumberMessage = ": invalid flight number/category\n";
    String insufficientSeatsMessage = ": Number of seats selected exceeds the available number of seats\n";
    String invalidCardNumberMessage = ": invalid card number\n";
    String csvColumnNamesList = "Booking Name,Flight Number,Category,Number of Seats,Total Price";

    public static void main(String[] args){

        RunClient runClient = new RunClient();

        sampleFilePath = args[0];
        flightsFilePath = args[1];
        outputCsvFilePath = args[2];
        outputTextFilePath = args[3];
        runClient.bookFlights();
    }

    public void bookFlights(){
        csvFileBuilder = new StringBuilder();
        csvFileBuilder.append(csvColumnNamesList +"\n");
        textFileBuilder = new StringBuilder();

        loadFlightsData();
        parseInputData();

    }

    public void loadFlightsData(){
        PopulateStaticDatabase db = PopulateStaticDatabase.getInstance();
        flightHashMap = db.getFlightData();
    }

    public void parseInputData(){
        try(
                BufferedReader br = new BufferedReader(new FileReader(RunClient.sampleFilePath));
                CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().withTrim(true).parse(br);
        ) {
            for (CSVRecord record : parser) {
                validateInputData(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validateInputData(CSVRecord record) throws IOException {
        String message = "";
        String bookingName = record.get("BookingName");

        PaymentValidator paymentValidator = new PaymentValidator(record, csvFileBuilder, flightHashMap, null);
        SeatsValidator seatsValidator = new SeatsValidator(record,csvFileBuilder, flightHashMap, paymentValidator);
        Validator flightNumberCategoryValidator = new FlightNumberCategoryValidator(record, csvFileBuilder,flightHashMap, seatsValidator);
        Validator validatorChain = flightNumberCategoryValidator;

        if(validatorChain.isValid()) {
            updateAvailableSeatsInDB(record);
            writeOutputToCSVFile(csvFileBuilder);
        }

        if(flightNumberCategoryValidator.getResult().equals("fail")) {
            message = errorMessage + bookingName + invalidFlightNumberMessage;
            textFileBuilder.append(message);
        }
        if(seatsValidator.getResult().equals("fail")) {
            message = errorMessage + bookingName + insufficientSeatsMessage;
            textFileBuilder.append(message);
        }
        if(paymentValidator.getResult().equals("fail")) {
            message = errorMessage + bookingName + invalidCardNumberMessage;
            textFileBuilder.append(message);
        }

        writeOutputToTextFile(textFileBuilder);
    }


    public void updateAvailableSeatsInDB(CSVRecord record){
        String flightNumber = record.get("flightNumber");
        String seatCategory = record.get("seatCategory");
        int numberOfSeatsSelected = Integer.parseInt(record.get("numberOfSeats"));
        Flight flight  = flightHashMap.getOrDefault(new Pair<>(flightNumber, seatCategory), null);
        flight.setAvailableSeats(flight.getAvailableSeats()-numberOfSeatsSelected);
        flightHashMap.put(new Pair<>(flightNumber, seatCategory), flight);
    }

    public void writeOutputToCSVFile(StringBuilder csvFileBuilder) throws IOException{
        new FileWriterFactory().getFileWriter("CSV").write(csvFileBuilder.toString());
    }

    public void writeOutputToTextFile(StringBuilder textFileBuilder) throws IOException {
        new FileWriterFactory().getFileWriter("TEXT").write(textFileBuilder.toString());
    }

}
