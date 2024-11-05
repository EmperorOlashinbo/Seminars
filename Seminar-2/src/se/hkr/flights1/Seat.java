package se.hkr.flights1;

class Seat {
    private String seatNumber;
    private String seatClass;
    private boolean isReserved;

    /*public Seat(String seatNumber, String seatClass, boolean isReserved) {
        this.seatNumber = seatNumber;
        this.seatClass = seatClass;
        this.isReserved = isReserved;
    }*/

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}