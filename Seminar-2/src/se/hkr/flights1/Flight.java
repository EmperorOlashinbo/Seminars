package se.hkr.flights1;

import java.time.LocalDateTime;
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
    private List<Seat> seats;
    private double price;

    /*public Flight(String flightNumber, String departureCity, String destinationCity,
                  LocalDateTime departureTime, LocalDateTime arrivalTime, double price) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.passengers = new ArrayList<>();
        this.bookedPassengers = new ArrayList<>();
        this.seats = new ArrayList<>();
    }*/

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

    public void setPassengers(List<String> passengers) {
        this.passengers = passengers;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<String> getBookedPassengers() {
        return bookedPassengers;
    }

    public void setBookedPassengers(List<String> bookedPassengers) {
        this.bookedPassengers = bookedPassengers;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    /*public List<Seat> getAvailableSeats() {

    }*/

    public void remove() {

    }
}
