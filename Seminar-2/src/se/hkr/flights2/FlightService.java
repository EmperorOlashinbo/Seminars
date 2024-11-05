package se.hkr.flights2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class FlightService {
    private List<Flight> flights;

    public void addFlight(Flight flight) throws FlightBookingException {
        for (Flight existingFlight : flights) {
            if (existingFlight.getFlightNumber().equals(flight.getFlightNumber())) {
                throw new FlightBookingException("Flight with the same number already exists.");
            }
        }
        flights.add(flight);
    }
    public List<Flight> searchFlights(String departureCity, String destinationCity) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            if ((departureCity.isEmpty() || flight.getDepartureCity().equalsIgnoreCase(departureCity))
                    && (destinationCity.isEmpty() || flight.getDestinationCity().equalsIgnoreCase(destinationCity))) {
                result.add(flight);
            }
        }
        return result;
    }
    public void addPassengerToFlight(String flightNumber, String passengerName) throws FlightBookingException {
        Flight flight = getFlightByNumber(flightNumber);
        if (flight == null) {
            throw new FlightBookingException("Flight not found");
        }
        if (flight.getPassengers().contains(passengerName)) {
            throw new FlightBookingException("Passenger is already booked on this flight");
        }
        flight.addPassenger(passengerName);
    }
    public void bookFlight ( String flightNumber, String passengerName) throws FlightBookingException {
        Flight flight = getFlightByNumber(flightNumber);
        if (flight == null) {
            throw new FlightBookingException("Flight not found");
        }
        flight.bookFlight(passengerName);
    }
    public void viewBookings(String flightNumber) throws FlightBookingException {
        Flight flight = getFlightByNumber(flightNumber);
        if (flight == null) {
            throw new FlightBookingException("Flight not found");
        }
        flight.displayBookedPassengers();
    }
    private Flight getFlightByNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }
    public void saveFlightsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("flights.dat"))) {
            oos.writeObject(flights);
        }
        catch (IOException error) {
            System.out.println("Error saving flights to file: " + error.getMessage());
        }
    }
    public void loadFlightsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("flights.dat"))) {
            flights = (List<Flight>) ois.readObject();
        }
        catch (IOException | ClassNotFoundException error) {
            flights = new ArrayList<>();
        }
    }
}