package se.hkr.flights1;

import java.util.ArrayList;
import java.util.List;

class Passenger {
    private String name;
    private String contactInformation;
    private String paymentMethod;
    private double discount;
    private List<Booking> bookings;

    /*public Passenger(String name, String contactInformation, String paymentMethod, double discount) {
        this.name = name;
        this.contactInformation = contactInformation;
        this.paymentMethod = paymentMethod;
        this.discount = discount;
        this.bookings = new ArrayList<>();
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void viewBookingHistory() {

    }
    /*public void updateDiscount(){

    }*/
}