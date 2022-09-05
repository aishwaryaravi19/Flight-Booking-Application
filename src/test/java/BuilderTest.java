import org.junit.Assert;
import org.junit.Test;
import test.model.Flight;

public class BuilderTest {
    @Test
    public void TestFlightObjectUsingBuilders() {
        Flight flight = new Flight.FlightBuilder("SJ456", "Economy").availableSeats(2).price(250).arrivalCity("Seattle").departureCity("San Jose").build();

        Assert.assertEquals("SJ456", flight.getFlightNumber());
        Assert.assertEquals("Economy", flight.getCategory());
        Assert.assertEquals(2, flight.getAvailableSeats());
        Assert.assertEquals(250, flight.getPrice());
        Assert.assertEquals("Seattle", flight.getArrivalCity());
        Assert.assertEquals("San Jose", flight.getDepartureCity());
    }

}
