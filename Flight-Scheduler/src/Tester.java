import java.sql.*;
import java.util.Arrays;

public class Tester {

	public static void main(String[] args) {
		Flight a = new Flight(1, "ATL", "BOS", new Timestamp(0), new Timestamp(0), 100, 200);
		Flight b = new Flight(2, "BOS", "ATL", new Timestamp(0), new Timestamp(0), 140, 250);
		System.out.println(a);
		
		Customer c = new Customer("graysont@bu.edu", "foobar");
		c.addTicket(a);
		c.addTicket(b);
		c.viewTickets();
	}
	
}
