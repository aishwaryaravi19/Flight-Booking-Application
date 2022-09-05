package test.validator;

import javafx.util.Pair;
import org.apache.commons.csv.CSVRecord;
import test.model.Flight;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;


public abstract class Validator {

    protected Flight flight;
    protected String bookingName;
    protected String flightNumber;
    protected String seatCategory;
    protected int numberOfSeatsSelected;
    protected BigDecimal paymentCardNumber;
    protected StringBuilder builder;
    protected CSVRecord record;
    protected HashMap<Pair<String, String>, Flight> flightHashMap;
    protected Validator nextValidator;

    public Validator(CSVRecord record, StringBuilder builder, HashMap<Pair<String, String>, Flight> flightHashMap, Validator nextValidator){
        this.record = record;
        this.builder = builder;
        this.nextValidator = nextValidator;
        this.flightHashMap = flightHashMap;
    }

    public abstract String getResult();

    public abstract boolean isValid() throws IOException;

}



