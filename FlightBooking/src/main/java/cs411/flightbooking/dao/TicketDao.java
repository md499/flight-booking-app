package cs411.flightbooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cs411.flightbooking.models.Ticket;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketDao implements DAO<Ticket> {

    /* Attributes */
    private Connection conn;

    /**
     * Constructor - initialize a connection with the specified database's name
     *
     * @param dbName is the name of a database name
     */
    public TicketDao(String dbName) {
        try {
            this.conn = DBConn.createConnection(dbName);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Constructor - initialize a connection with the default database name
     * cs411
     *
     */
    public TicketDao() {
        try {
            this.conn = DBConn.defaultConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * getTicketForUser -
     *
     * @param email is the email of a user
     * @return all tickets belong to this user
     */
    public List<Ticket> getTicketsForUser(String email) {
        String GET_TICKETS_SQL = "SELECT `ticket`.`user_email`,\n"
                + "    `ticket`.`flight_id`\n"
                + "FROM `cs411`.`ticket` where user_email = ?;";

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
        // MySQL command for adding ticket info
        String INSERT_TICKET_SQL = "INSERT INTO `cs411`.`ticket`\n"
                + "(`user_email`,\n"
                + "`flight_id`)\n"
                + "VALUES\n"
                + "(?,?);";

        int result = 0;

        try {
            // Statements for adding ticket info
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
