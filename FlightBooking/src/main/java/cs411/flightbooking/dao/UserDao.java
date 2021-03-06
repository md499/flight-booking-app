package cs411.flightbooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cs411.flightbooking.models.User;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UserDao - a class for communicating between a user object and the database
 *
 */
public class UserDao implements DAO<User> {

    /* Attributes */
    private Connection conn;

    /**
     * Constructor - initialize a connection with the specified database's name
     *
     * @param dbName is the name of a database name
     */
    public UserDao(String dbName) {
        try {
            this.conn = DBConn.createConnection(dbName);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Constructor - initialize a connection with the default database name
     * cs411
     */
    public UserDao() {
        try {
            this.conn = DBConn.defaultConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * getUser -
     *
     * @param email is the email of the user
     * @return a User object with the matching email from the database
     */
    public User getUser(String email) {

        String GET_USER_SQL = "SELECT `users`.`firstName`,`users`.`lastName`,`users`.`email`,`users`.`password` "
                + "FROM `cs411`.`users` where email = ?";

        User user = null;

        try {
            PreparedStatement statements = this.conn.prepareStatement(GET_USER_SQL);
            statements.setString(1, email);

            ResultSet rs = statements.executeQuery();

            rs.next();
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String emails = rs.getString("email");
            String password = rs.getString("password");

            user = new User(firstName, lastName, emails, password);

            return user;

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;

    }

    @Override
    public int insert(User user) {
        // MySQL command for adding user info
        String INSERT_USER_SQL = "INSERT INTO users"
                + " (firstName, lastName, email, password) VALUES"
                + " (?, ?, ?, ?)";

        // Establish connection with mysql server
        int result = 0;

        System.out.println(user);

        try {
            // Statements for adding user info
            PreparedStatement statements = this.conn.prepareStatement(INSERT_USER_SQL);
            statements.setString(1, user.getFirstName());
            statements.setString(2, user.getLastName());
            statements.setString(3, user.getEmail());
            statements.setString(4, user.getPassword());

            System.out.println("Connection Successful!");
            System.out.println("Statement: " + statements);

            result = statements.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FlightDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /* Testing */
    public static void main(String[] args) {
        User user = new User("min", "le", "min@bu.ed", "12345");
        UserDao userdao = new UserDao();

        userdao.insert(user);
    }
}
