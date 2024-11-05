package se.hkr.flights2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class Flight {
    private String flightNumber;
    private String departureCity;
    private String destinationCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private List<String> passengers;
    private List<String> bookedPassengers;

    public Flight(String flightNumber, String departureCity, String destinationCity,
                  LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.passengers = new ArrayList<>();
        this.bookedPassengers = new ArrayList<>();
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public List<String> getBookedPassengers() {
        return bookedPassengers;
    }
    public void addPassenger(String passengerName) {
        passengers.add(passengerName);
    }
    public void bookFlight(String passengerName) throws FlightBookingException {
        if (bookedPassengers.contains(passengerName)) {
            throw new FlightBookingException("Passenger is already booked on this flight");
        }
        bookedPassengers.add(passengerName);
    }
    public void displayBookedPassengers() {
        if (bookedPassengers.isEmpty()) {
            System.out.println("No passengers booked for this flight.");
        }
        else {
            System.out.println("Booked Passengers:");
            for (String passenger : bookedPassengers) {
                System.out.println(passenger);
            }
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return "Flight Number: " + flightNumber +
                "\nDeparture City: " + departureCity +
                "\nDestination City: " + destinationCity +
                "\nDeparture Time: " + departureTime.format(formatter) +
                "\nArrival Time: " + arrivalTime.format(formatter);
    }
}
