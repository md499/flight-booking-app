package cs411.flightbooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cs411.flightbooking.models.Flight;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UserDao - a class for communicating between a user object and the database
 *
 */
public class FlightDao implements DAO<Flight> {

    private Connection conn;

    public FlightDao(String dbName) {
        try {
            this.conn = DBConn.createConnection(dbName);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FlightDao() {
        try {
            this.conn = DBConn.defaultConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * insert a new flight into the database
     *
     * @param flight
     * @return
     */
    @Override
    public int insert(Flight flight) {

        // MySQL command for adding user info
        String INSERT_FLIGHT_SQL = "INSERT INTO flight"
                + " (`flight_id`,"
                + "`departureLocation`,"
                + "`arrivalLocation`,"
                + "`departureTime`,"
                + "`arrivalTime`,"
                + "`capacity`,"
                + "`available`,"
                + "`price`)"
                + "VALUES"
                + " (?, ?, ?, ?, ?, ?, ?, ?)";

        int result = -1;

        //System.out.println(flight);
        try {
            // Statements for adding flight info
            PreparedStatement statement = this.conn.prepareStatement(INSERT_FLIGHT_SQL);

            statement.setInt(1, flight.getId());

            statement.setString(2, flight.getDepartureLocation());
            statement.setString(3, flight.getArrivalLocation());
            statement.setTimestamp(4, Timestamp.valueOf(flight.getDepartureTime()));
            statement.setTimestamp(5, Timestamp.valueOf(flight.getArrivalTime()));

            statement.setInt(6, flight.getCapacity());
            statement.setInt(7, flight.getAvailable());
            statement.setDouble(8, flight.getPrice());

            System.out.println("Connection Successful!");
            System.out.println("Statement: " + statement);

            result = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FlightDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<Flight> selectAllFlights() {
        List<Flight> flights = new ArrayList<>();

        String SELECT_FLIGHT_SQL = "SELECT * FROM flight";

        try {
            PreparedStatement statement = this.conn.prepareStatement(SELECT_FLIGHT_SQL);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("flight_id");

                String departLoc = rs.getString("departureLocation");
                String arrivalLoc = rs.getString("arrivalLocation");

                LocalDateTime departTime = rs.getTimestamp("departureTime").toLocalDateTime();
                LocalDateTime arrivalTime = rs.getTimestamp("arrivalTime").toLocalDateTime();

                int capacity = rs.getInt("capacity");
                int available = rs.getInt("available");
                double price = rs.getDouble("price");

                Flight flight = new Flight(id, departLoc, arrivalLoc, departTime, arrivalTime, capacity, available, price);

                flights.add(flight);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlightDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flights;
    }

    public static Flight getFlight(int id) {
        return null;
    }

    public int updateFlight(Flight newFlight) {
        int result = 0;

        String selectQuery = "Select * from flight where id = " + newFlight.getId();
        String updateQuery = "UPDATE `flight`\n"
                + "SET\n"
                + "`departureLocation` = ?,\n"
                + "`arrivalLocation` = ?,\n"
                + "`departureTime` = ?,\n"
                + "`arrivalTime` = ?,\n"
                + "`capacity` = ?,\n"
                + "`available` = ?,\n"
                + "`price` = ?\n"
                + "WHERE `flight_id` = ?;";

        try {
            PreparedStatement stm = this.conn.prepareStatement(selectQuery);
            ResultSet rs = stm.executeQuery();
            rs.next();

            stm = this.conn.prepareStatement(updateQuery);
            stm.setString(1, newFlight.getDepartureLocation());
            stm.setString(2, newFlight.getArrivalLocation());

            stm.setTimestamp(4, Timestamp.valueOf(newFlight.getDepartureTime()));
            stm.setTimestamp(5, Timestamp.valueOf(newFlight.getArrivalTime()));

            stm.setInt(6, newFlight.getCapacity());
            stm.setInt(7, newFlight.getCapacity() - rs.getInt("capacity") + rs.getInt("available"));
            stm.setDouble(8, newFlight.getPrice());

            result = stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(FlightDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static void main(String[] args) {
        FlightDao flightdao = new FlightDao();
//        Flight flight = new Flight(2, "BOS", "JFK", "2023-05-14 07:00:00", "2023-05-14 10:00:00", 200, 150, 399.99, "ON-TIME");

//        flightdao.insert(flight);
        List<Flight> flights = flightdao.selectAllFlights();

        for (Flight myFlight : flights) {
            System.out.println(myFlight);
        }
    }
}
