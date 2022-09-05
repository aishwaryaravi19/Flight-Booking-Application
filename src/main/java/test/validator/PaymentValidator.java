package test.validator;

import javafx.util.Pair;
import org.apache.commons.csv.CSVRecord;
import test.model.Flight;

import java.math.BigDecimal;
import java.util.HashMap;

public class PaymentValidator extends Validator {

    String result="pass";

    public PaymentValidator(CSVRecord record, StringBuilder stringBuilder, HashMap<Pair<String, String>, Flight> flightHashMap, Validator nextValidator) {
        super(record, stringBuilder, flightHashMap, nextValidator);
    }

    @Override
    public boolean isValid() {
        bookingName = record.get("BookingName");
        flightNumber = record.get("flightNumber");
        seatCategory = record.get("seatCategory");
        numberOfSeatsSelected = Integer.parseInt(record.get("numberOfSeats"));

        flight  = flightHashMap.getOrDefault(new Pair<>(flightNumber, seatCategory), null);
        int totalPrice = numberOfSeatsSelected * flight.getPrice();

        CardValidator visaCardValidator = new VisaCardValidator(null);
        CardValidator masterCardValidator = new MasterCardValidator(visaCardValidator);
        CardValidator discoverCardValidator = new DiscoverCardValidator(masterCardValidator);
        CardValidator chainValidator = new AmexCardValidator(discoverCardValidator);
        paymentCardNumber = BigDecimal.valueOf(Long.valueOf(record.get("paymentCardNumber")));

        if (chainValidator.isValidCard(paymentCardNumber)) {
            builder.append(bookingName +",");
            builder.append(flightNumber + ",");
            builder.append(seatCategory + ",");
            builder.append(numberOfSeatsSelected + ",");
            builder.append(totalPrice);
            builder.append('\n');
        }
        else {
           result = "fail";
        }

        return true;
    }

    public String getResult(){
        return result;
    }
}
