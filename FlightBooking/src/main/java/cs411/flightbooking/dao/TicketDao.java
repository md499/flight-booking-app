package cs411.flightbooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cs411.flightbooking.models.Ticket;
import java.sql.ResultSet;
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
