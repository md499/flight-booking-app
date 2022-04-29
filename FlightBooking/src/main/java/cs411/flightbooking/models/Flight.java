package cs411.flightbooking.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight {

    private static final DateTimeFormatter DATETIME_FORMAT
            = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private final int id;
    private final String departureLocation;
    private final String arrivalLocation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int capacity;
    private int available;
    private double price;

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

    public int getId() {
        return id;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public String getDepartureTimeString() {
        return departureTime.format(DATETIME_FORMAT);
    }

    public String getArrivalTimeString() {
        return arrivalTime.format(DATETIME_FORMAT);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailable() {
        return available;
    }

    public double getPrice() {
        return price;
    }

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

    public static void main(String[] args) {
        Flight flight = new Flight(0, "JFK", "BOS", "2022-04-14 05:30:00", "2022-04-15 01:00:00", 200, 200, 99.99);
        Flight flight1 = new Flight(0, "JFK", "BOS", LocalDateTime.now(), LocalDateTime.now(), 200, 200, 99.99);

        System.out.println(flight1);

        LocalDateTime dt = LocalDateTime.parse("2022-04-20T06:28");
        System.out.println(dt);

    }
}
