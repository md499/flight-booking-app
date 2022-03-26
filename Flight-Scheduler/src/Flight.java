import java.sql.*;

public class Flight {
	private int id;
	private String departureLocation;
	private String arrivalLocation;
	private Timestamp departureTime;
	private Timestamp arrivalTime;
	private int capacity;
	private int price;
	
	
	public Flight(int thisId, String depLoc, String arrLoc, Timestamp depTime, Timestamp arrTime, int c, int p) {
		this.id = thisId;
		this.departureLocation = depLoc;
		this.arrivalLocation = arrLoc;
		this.departureTime = depTime;
		this.arrivalTime = arrTime;
		this.capacity = c;
		this.price = p;
	}
	
	public String getDepartureLocation() {
		return this.departureLocation;
	}
	public void setDepartureLocation(String location) {
		this.departureLocation = location;
	}
	
	public String getArrivalLocation() {
		return this.arrivalLocation;
	}
	public void setArrivalLocation(String location) {
		this.arrivalLocation = location;
	}
	
	public Timestamp getDepartureTime() {
		return this.departureTime;
	}
	public void setDepartureTime(Timestamp t) {
		this.departureTime = t;
	}
	
	public Timestamp getArrivalTime() {
		return this.arrivalTime;
	}
	public void setArrivalTime(Timestamp t) {
		this.arrivalTime = t;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	public void setCapacity(int c) {
		this.capacity = c;
	}
	
	public int getPrice() {
		return this.price;
	}
	public void setPrice(int p) {
		this.price = p;
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int idInput) {
		this.id = idInput;
	}
	
	public String toString() {
		return "Flight " + this.id + ": From " + this.departureLocation + " on (" + 
	this.departureTime + ") to " + this.arrivalLocation + " on (" + this.arrivalTime + "), Price: $"
	+ this.price;
	}

}
