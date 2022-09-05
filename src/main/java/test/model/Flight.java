package test.model;

public class Flight {
    private String category;
    private String flightNumber;
    private int availableSeats;
    private int price;
    private String arrivalCity;
    private String departureCity;

    public Flight(FlightBuilder builder) {
        this.flightNumber = builder.flightNumber;
        this.category = builder.category;
        this.availableSeats = builder.availableSeats;
        this.price = builder.price;
        this.arrivalCity = builder.arrivalCity;
        this.departureCity = builder.departureCity;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public static class FlightBuilder
    {
        private String category;
        private String flightNumber;
        private int availableSeats;
        private int price;
        private String arrivalCity;
        private String departureCity;

        public FlightBuilder(String flightNumber, String category) {
            this.flightNumber = flightNumber;
            this.category = category;
        }
        public FlightBuilder availableSeats(int availableSeats) {
            this.availableSeats = availableSeats;
            return this;
        }
        public FlightBuilder price(int price) {
            this.price = price;
            return this;
        }
        public FlightBuilder arrivalCity(String arrivalCity) {
            this.arrivalCity = arrivalCity;
            return this;
        }
        public FlightBuilder departureCity(String departureCity) {
            this.departureCity = departureCity;
            return this;
        }

        public Flight build() {
            Flight flight =  new Flight(this);
            return flight;
        }
    }
}
