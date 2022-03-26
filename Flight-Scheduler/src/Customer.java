import java.util.*;

public class Customer {
	private String email;
	private String password;
	private ArrayList<Flight> tickets;
	
	public Customer(String e, String p) {
		this.email = e;
		this.password = p;
	}
	
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String e) {
		this.email = e;
	}
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String p) {
		this.password = p;
	}
	
	public void addTicket(Flight f) {
		this.tickets.add(f);
	}
	public ArrayList<Flight> viewTickets() {
		return this.tickets;
	}

}
