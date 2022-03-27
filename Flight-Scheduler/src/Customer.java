import java.util.*;

public class Customer extends User {

	private ArrayList<Flight> tickets = new ArrayList<>();
	
	public Customer (String email, String password) {
		super(email, password);
	}
	
	public void addTicket(Flight f) {
		tickets.add(f);
	}
	
	public void viewTickets() {
		System.out.println("Customer " + this.getEmail() + " tickets: ");
		for (Flight f : tickets) {
			System.out.println(f);
		}
	}

}
