import java.sql.*;
import java.util.Arrays;
import java.util.*;


public class Tester {
	//public ArrayList<User> users = new ArrayList<User>(10);
	// User register -> User {email, password}
	// users = {"email": user object}
	public static  Hashtable<String, User> users = new Hashtable<String, User>();
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
/* 		Flight a = new Flight(1, "ATL", "BOS", new Timestamp(0), new Timestamp(0), 100, 200);
		Flight b = new Flight(2, "BOS", "ATL", new Timestamp(0), new Timestamp(0), 140, 250);
		System.out.println(a);
		
		Customer c = new Customer("graysont@bu.edu", "foobar");
		c.addTicket(a);
		c.addTicket(b);
		c.viewTickets(); */

		printMenu();
	
}

public static void custLogin()
{ 
	System.out.println("Please enter your email:");

	String email = sc.nextLine();

	System.out.println("Please enter your password:");

	String password = sc.nextLine(); 

	if (users.containsKey(email)) {
		users.get(email).authenticate(password);
	}

}

public static void printMenu()
{

	System.out.println("WELCOME");
	System.out.println("1. Customer Login");
	System.out.println("2. Admin Login");
	System.out.println("3. View Flight Details");


	
	System.out.println("Please enter you choice:"); 
	String option = sc.nextLine();


	String opt;

	switch (option) {
		case "1": custLogin();
		break;
		case "2": //adLogin(); 
		break;
		case "3": //flightDetails();
		break;
		default: System.out.println("Invalid Option. Please enter again!"); printMenu();
	}
	

// Need register method 

}


}
