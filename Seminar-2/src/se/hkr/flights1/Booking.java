package se.hkr.flights1;

class Booking {
    private String bookingReference;
    private Flight flight;
    private Seat seat;
    private Passenger passenger;

    /*public Booking(String bookingReference, Flight flight, Seat seat, Passenger passenger) {
        this.bookingReference = bookingReference;
        this.flight = flight;
        this.seat = seat;
        this.passenger = passenger;
    }*/
    public void cancelBooking() {

    }

    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}