package se.hkr.flights2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        FlightService flightService = new FlightService();
        flightService.loadFlightsFromFile();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Flight");
            System.out.println("2. Add Passenger");
            System.out.println("3. Book a Flight");
            System.out.println("4. Search Flights");
            System.out.println("5. View Bookings");
            System.out.println("6. Save and Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addFlight(scanner, flightService);
                    break;
                case 2:
                    addPassengerToFlight(scanner, flightService);
                case 3:
                    bookFlight(scanner, flightService);
                    break;
                case 4:
                    searchFlights(scanner, flightService);
                    break;
                case 5:
                    viewBookings(scanner, flightService);
                    break;
                case 6:
                    flightService.saveFlightsToFile();
                    System.out.println("Flights saved. Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private static void addFlight(Scanner scanner, FlightService flightService) {
        Flight flight = new Flight("ABC123", "CityA", "CityB", LocalDateTime.now(), LocalDateTime.now().plusHours(2));

        System.out.print("Enter Flight Number: ");
        flight.setFlightNumber(scanner.nextLine());
        System.out.print("Enter Departure City: ");
        flight.setDepartureCity(scanner.nextLine());
        System.out.print("Enter Destination City: ");
        flight.setDestinationCity(scanner.nextLine());

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm ")
                .appendOffset("+HH:MM", "+00:00")
                .toFormatter();

        System.out.print("Enter Departure Time (yyyy-MM-dd HH:mm +/-HH:mm): ");
        flight.setDepartureTime(LocalDateTime.parse(scanner.nextLine(), formatter));

        System.out.print("Enter Arrival Time (yyyy-MM-dd HH:mm +/-HH:mm): ");
        flight.setArrivalTime(LocalDateTime.parse(scanner.nextLine(), formatter));

        try {
            flightService.addFlight(flight);
            System.out.println("Flight added successfully.");
        }
        catch (FlightBookingException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }
    private static void addPassengerToFlight(Scanner scanner, FlightService flightService) {
        System.out.print("Enter Flight Number: ");
        String flightNumber = scanner.nextLine();
        System.out.print("Enter Passenger Name: ");
        String passengerName = scanner.nextLine();

        try {
            flightService.addPassengerToFlight(flightNumber, passengerName);
            System.out.println("Passenger added to the flight successfully.");
        }
        catch (FlightBookingException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }
    private static void bookFlight(Scanner scanner, FlightService flightService) {
        System.out.print("Enter Flight Number: ");
        String flightNumber = scanner.nextLine();
        System.out.print("Enter Passenger Name: ");
        String passengerName = scanner.nextLine();

        try {
            flightService.bookFlight(flightNumber, passengerName);
            System.out.println("Flight booked successfully.");
        }
        catch (FlightBookingException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }
    private static void viewBookings(Scanner scanner, FlightService flightService) {
        System.out.print("Enter Flight Number: ");
        String flightNumber = scanner.nextLine();

        try {
            flightService.viewBookings(flightNumber);
        }
        catch (FlightBookingException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }
    private static void searchFlights(Scanner scanner, FlightService flightService) {
        System.out.print("Enter Departure City (leave blank for any): ");
        String departureCity = scanner.nextLine();
        System.out.print("Enter Destination City (leave blank for any): ");
        String destinationCity = scanner.nextLine();

        List<Flight> foundFlights = flightService.searchFlights(departureCity, destinationCity);

        System.out.println("Found Flights:");
        for (Flight flight : foundFlights) {
            System.out.println(flight);
        }
    }
}