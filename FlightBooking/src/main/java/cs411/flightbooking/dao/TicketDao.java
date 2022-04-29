package cs411.flightbooking.dao;

import cs411.flightbooking.models.Flight;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cs411.flightbooking.models.Ticket;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketDao implements DAO<Ticket> {

    private Connection conn;

    public TicketDao(String dbName) {
        try {
            this.conn = DBConn.createConnection(dbName);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TicketDao() {
        try {
            this.conn = DBConn.defaultConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Ticket> getFlightsFromSearch(String email) {
        String GET_TICKETS_SQL = "SELECT `ticket`.`user_email`,\n" +
                                "    `ticket`.`flight_id`\n" +
                                "FROM `cs411`.`ticket` where user_email = ?;";

        List<Ticket> tickets = new ArrayList<>();

        try {
            PreparedStatement statements = this.conn.prepareStatement(GET_TICKETS_SQL);
            statements.setString(1, email);

            ResultSet rs = statements.executeQuery();

            while (rs.next()) {
                String user_email = rs.getString("user_email");
                int id = rs.getInt("flight_id");
                Ticket ticket = new Ticket(user_email, id);

                tickets.add(ticket);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tickets;
    }

    @Override
    public int insert(Ticket ticket) {
        // MySQL command for adding user info
        String INSERT_TICKET_SQL = "INSERT INTO `cs411`.`ticket`\n"
                + "(`user_email`,\n"
                + "`flight_id`)\n"
                + "VALUES\n"
                + "(?,?);";

        // Establish connection with mysql server
        int result = 0;

        try {
            // Statements for adding user info
            PreparedStatement statements = this.conn.prepareStatement(INSERT_TICKET_SQL);
            statements.setString(1, ticket.getUserEmail());
            statements.setInt(2, ticket.getFlightId());

            System.out.println("Connection Successful!");
            System.out.println("Statement: " + statements);

            result = statements.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FlightDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    

}
