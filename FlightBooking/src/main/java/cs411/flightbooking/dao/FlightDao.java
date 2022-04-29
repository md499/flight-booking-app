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

    public List<Flight> getFlightFromSearch(String DLocation, String ALocation, String Date) {
        String GET_FLIGHT_SQL = "SELECT `flight`.`flight_id`,`flight`.`departureLocation`,\n"
                + "`flight`.`arrivalLocation`,`flight`.`departureTime`,`flight`.`arrivalTime`,\n"
                + "                `flight`.`capacity`,`flight`.`available`,`flight`.`price`  \n"
                + "                FROM `cs411`.`flight` where departureLocation = ? AND arrivalLocation = ? AND departureTime >= ?";

        List<Flight> flights = new ArrayList<>();

        try {
            PreparedStatement statements = this.conn.prepareStatement(GET_FLIGHT_SQL);
            statements.setString(1, DLocation);
            statements.setString(2, ALocation);
            //statements.setTimestamp(3, Timestamp.valueOf(LocalDateTime.parse(Date)));
            statements.setString(3, Date);

            ResultSet rs = statements.executeQuery();

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
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flights;
    }

    public int update(Flight newFlight) {
        int result = 0;
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
            PreparedStatement stm = this.conn.prepareStatement(updateQuery);
            stm.setString(1, newFlight.getDepartureLocation());
            stm.setString(2, newFlight.getArrivalLocation());

            stm.setTimestamp(3, Timestamp.valueOf(newFlight.getDepartureTime()));
            stm.setTimestamp(4, Timestamp.valueOf(newFlight.getArrivalTime()));

            stm.setInt(5, newFlight.getCapacity());
            stm.setInt(6, newFlight.getCapacity());
            stm.setDouble(7, newFlight.getPrice());
            stm.setDouble(8, newFlight.getId());

            result = stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(FlightDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     * remove - a function that helps removing a flight from the database
     *
     * @param flightID is the id of the targeted flight
     * @return the number of row affected by the execution of the delete query
     */
    public int remove(int flightID) {
        int result = 0;
        String deleteQuery = "DELETE FROM `cs411`.`flight`"
                + "WHERE flight_id = ?;";

        try {
            PreparedStatement stm = this.conn.prepareStatement(deleteQuery);
            stm.setInt(1, flightID);
            result = stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(FlightDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public Flight getFlight(int flight_id) {
        String GET_FLIGHT_QUERY = "SELECT `flight`.`flight_id`,\n"
                + "`flight`.`departureLocation`,\n"
                + "`flight`.`arrivalLocation`,\n"
                + "`flight`.`departureTime`,\n"
                + "`flight`.`arrivalTime`,\n"
                + "`flight`.`capacity`,\n"
                + "`flight`.`available`,\n"
                + "`flight`.`price`\n"
                + "FROM `cs411`.`flight` WHERE flight_id = ?";

        Flight bookedFlight = null;

        try {
            PreparedStatement statements = this.conn.prepareStatement(GET_FLIGHT_QUERY);
            statements.setInt(1, flight_id);

            ResultSet rs = statements.executeQuery();

            rs.next();
            int id = rs.getInt("flight_id");

            String departLoc = rs.getString("departureLocation");
            String arrivalLoc = rs.getString("arrivalLocation");

            LocalDateTime departTime = rs.getTimestamp("departureTime").toLocalDateTime();
            LocalDateTime arrivalTime = rs.getTimestamp("arrivalTime").toLocalDateTime();

            int capacity = rs.getInt("capacity");
            int available = rs.getInt("available");
            double price = rs.getDouble("price");

            bookedFlight = new Flight(id, departLoc, arrivalLoc, departTime, arrivalTime, capacity, available, price);

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookedFlight;

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
