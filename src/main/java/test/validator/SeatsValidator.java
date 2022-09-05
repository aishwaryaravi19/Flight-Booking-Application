package test.validator;

import javafx.util.Pair;
import org.apache.commons.csv.CSVRecord;
import test.model.Flight;

import java.io.IOException;
import java.util.HashMap;

public class SeatsValidator<T> extends Validator {

    String result="pass";
    public SeatsValidator(CSVRecord record, StringBuilder stringBuilder, HashMap<Pair<String, String>, Flight> flightHashMap, Validator nextValidator) {
        super(record, stringBuilder, flightHashMap, nextValidator);
    }

    @Override
    public boolean isValid() throws IOException {
        numberOfSeatsSelected = Integer.parseInt(record.get("numberOfSeats"));
        flightNumber = record.get("flightNumber");
        seatCategory = record.get("seatCategory");
        flight  = flightHashMap.getOrDefault(new Pair<>(flightNumber, seatCategory), null);
        if (flight.getAvailableSeats() >= numberOfSeatsSelected) {
            if (nextValidator != null) {
                return nextValidator.isValid();
            }
        } else {
            result = "fail";
        }
        return false;
    }

    public String getResult(){
        return result;
    }
}
