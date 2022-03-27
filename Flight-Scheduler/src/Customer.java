import java.util.*;

public class Customer {
	private String email;
	private String password;
	private ArrayList<Flight> tickets = new ArrayList<>();
	
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
		tickets.add(f);
	}
	
	public void viewTickets() {
		System.out.println("Customer " + this.email + " tickets: ");
		for (Flight f : tickets) {
			System.out.println(f);
		}
	}

}
