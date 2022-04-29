package cs411.flightbooking.models;

/**
 * Ticket - a class for representing a relationship between a user and a flight
 * through user's email and flight's id
 */
public class Ticket {

    private final String userEmail;
    private final int flightId;

    public Ticket(String email, int flightId) {
        this.userEmail = email;
        this.flightId = flightId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getFlightId() {
        return flightId;
    }
}
