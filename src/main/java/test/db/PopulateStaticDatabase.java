package test.db;

import javafx.util.Pair;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import test.RunClient;
import test.model.Flight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class PopulateStaticDatabase {
    private HashMap<Pair<String, String>, Flight> flightHashMap = new HashMap<Pair<String, String>, Flight>();

    private PopulateStaticDatabase(){

    }

    private volatile static PopulateStaticDatabase uniqueDbInstance;

    public static PopulateStaticDatabase getInstance() {
        if (uniqueDbInstance == null) {
            synchronized (PopulateStaticDatabase.class) {
                if (uniqueDbInstance == null) {
                    uniqueDbInstance = new PopulateStaticDatabase();
                }
            }
        }
        return uniqueDbInstance;
    }


    public HashMap<Pair<String, String>, Flight> getFlightData() {

        try (
                BufferedReader br = new BufferedReader(new FileReader(RunClient.flightsFilePath));
                CSVParser parser = CSVFormat.DEFAULT.withHeader().withTrim().parse(br);
        ) {
            for (CSVRecord record : parser) {

                String seatCategory = record.get(0);
                String flightNumber = record.get(1);
                int availableSeats = Integer.parseInt(record.get(2));
                int price = Integer.parseInt(record.get(3));
                String arrivalCity = record.get(4);
                String departureCity = record.get(5);
                Pair flightPair = new Pair(flightNumber, seatCategory);
                flightHashMap.put(flightPair, new Flight.FlightBuilder(flightNumber, seatCategory).availableSeats(availableSeats).price(price).arrivalCity(arrivalCity).departureCity(departureCity).build());
            }
        } catch (FileNotFoundException e) {

        }
        catch(IOException e) {

        }
        return flightHashMap;
    }
}
