package cs411.flightbooking.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Flight - a class for representing the flight objects
 * 
 * 
 */
public class Flight {

    /* Default date format */
    private static final DateTimeFormatter DATETIME_FORMAT
            = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
    /* Attributes declaration */
    private final int id;
    private final String departureLocation;
    private final String arrivalLocation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int capacity;
    private int available;
    private double price;
    
    /***
     * Constructor -  
     * @param id
     * @param departLoc
     * @param arrivalLoc
     * @param departTime
     * @param arrivalTime
     * @param capacity
     * @param available
     * @param price
     */
    public Flight(int id, String departLoc, String arrivalLoc,
            LocalDateTime departTime, LocalDateTime arrivalTime,
            int capacity, int available, double price) {
        this.id = id;

        this.departureLocation = departLoc;
        this.arrivalLocation = arrivalLoc;

        this.departureTime = departTime;
        this.arrivalTime = arrivalTime;

        this.capacity = capacity;
        this.available = available;
        this.price = price;
    }

    public Flight(int id, String departLoc, String arrivalLoc,
            String departTime, String arrivalTime,
            int capacity, int available, double price) {
        this(id, departLoc, arrivalLoc,
                LocalDateTime.parse(departTime, DATETIME_FORMAT),
                LocalDateTime.parse(arrivalTime, DATETIME_FORMAT),
                capacity, available, price);
    }
    
    public Flight(String departLoc, String arrivalLoc,
            LocalDateTime departTime, LocalDateTime arrivalTime,
            int capacity, int available, double price) {
        this(0, departLoc, arrivalLoc, departTime, arrivalTime, capacity, available, price);
    }

    
    public Flight(String departLoc, String arrivalLoc,
            String departTime, String arrivalTime,
            int capacity, int available, double price) {
        this(0, departLoc, arrivalLoc, departTime, arrivalTime, capacity, available, price);
    }
    
    /**
     * getId - getter method for the ID of the flight
     *
     * @return the ID of the flight
     */
    public int getId() {
        return id;
    }
    
    /**
     * getDepartureLocation - getter method for the departure location of the flight
     *
     * @return the departure location of the flight
     */
    public String getDepartureLocation() {
        return departureLocation;
    }

    /**
     * getArrivalLocation - getter method for the arrival location of the flight
     *
     * @return the arrival location of the flight
     */
    public String getArrivalLocation() {
        return arrivalLocation;
    }

    /**
     * getDepartureTime - getter method for the departure time of the flight
     *
     * @return the departure time of the flight
     */
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    
    /**
     * getArrivalTime - getter method for the arrival time of the flight
     *
     * @return the arrival time of the flight
     */
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    /**
     * getDepartureTimeString - convert DATETIME_FORMAT to String
     *
     * @return the string of departure time of the flight 
     */
    public String getDepartureTimeString() {
        return departureTime.format(DATETIME_FORMAT);
    }

    /**
     * getArrivalTimeString - convert DATETIME_FORMAT to String
     *
     * @return the string of arrival time of the flight 
     */
    public String getArrivalTimeString() {
        return arrivalTime.format(DATETIME_FORMAT);
    }

    /**
     * getCapacity - getter method for the capacity of the flight
     *
     * @return the capacity of the flight 
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * getAvailable - getter method for the number of available seats in 
     * the flight
     *
     * @return the number of available seats in the flight
     */
    public int getAvailable() {
        return available;
    }

    /**
     * getPrice - getter method for the price of a seat in the flight
     *
     * @return the price of a seat in the flight
     */
    public double getPrice() {
        return price;
    }

    /**
     * isBooked - method that updates the available seats in the flight when
     * one seat is booked
     *
     */
    public void isBooked() {
        this.available -= 1;
    }

    /**
     *
     * @return the String representation of a Flight object
     */
    @Override
    public String toString() {
        return "Flight_ID: " + this.getId() + "\n"
                + "From: " + this.getDepartureLocation() + "        To: " + this.getArrivalLocation() + "\n"
                + "Depart: " + this.getDepartureTimeString() + "    Arrival: " + this.getArrivalTimeString() + "\n"
                + "Capcity: " + this.getCapacity() + "\n"
                + "Available: " + this.getAvailable() + "\n"
                + "Price: " + this.getPrice() + "\n";
    }

    /**
     * Testing
     */
    public static void main(String[] args) {
        Flight flight = new Flight(0, "JFK", "BOS", "2022-04-14 05:30:00", "2022-04-15 01:00:00", 200, 200, 99.99);
        Flight flight1 = new Flight(0, "JFK", "BOS", LocalDateTime.now(), LocalDateTime.now(), 200, 200, 99.99);

        System.out.println(flight1);

        LocalDateTime dt = LocalDateTime.parse("2022-04-20T06:28");
        System.out.println(dt);

    }
}
