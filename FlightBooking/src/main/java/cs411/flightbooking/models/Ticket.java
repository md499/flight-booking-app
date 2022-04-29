package cs411.flightbooking.models;

/**
 * Ticket - a class for representing a relationship between a user and a flight
 * through user's email and flight's id
 * 
 */
public class Ticket {

    /* Attributes declaration*/
    private final String userEmail;
    private final int flightId;

    /***
     * Constructor
     * @param email
     * @param flightId
     */
    public Ticket(String email, int flightId) {
        this.userEmail = email;
        this.flightId = flightId;
    }

    /**
     * getUserEmail - getter method for the email of the user registered on a 
     * ticket
     *
     * @return the email of the user
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * getFlightId - getter method for the ID of the flight registered on a 
     * ticket
     *
     * @return the ID of the flight
     */
    public int getFlightId() {
        return flightId;
    }
}
