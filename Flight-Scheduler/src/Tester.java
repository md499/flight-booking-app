import java.sql.*;

public class Tester {

	public static void main(String[] args) {
		Flight a = new Flight(1, "ATL", "BOS", new Timestamp(0), new Timestamp(0), 100, 200);
		System.out.println(a);
	}
	
}
