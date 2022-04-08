package cs411.flightbooking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cs411.flightbooking.models.User;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UserDao - a class for communicating between a user object and the database
 *
 */
public class UserDao implements DAO<User> {

    /**
     * insert a newly registered user into the database
     *
     * @param user
     * @return
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public int insert(User user) throws ClassNotFoundException {
        // MySQL command for adding user info
        String INSERT_USER_SQL = "INSERT INTO users"
                + " (firstName, lastName, email, password) VALUES"
                + " (?, ?, ?, ?)";

        // Establish connection with mysql server
        String url = "jdbc:mysql://localhost:3306/cs411";
        String username = "root";
        String password = "12MySQL34!";

        int result = 0;

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            } catch (Exception e) {
                System.out.println("Instantiation Error");
            }

            Connection conn = DriverManager.getConnection(url, username, password);

            // Statements for adding user info
            PreparedStatement statements = conn.prepareStatement(INSERT_USER_SQL);
            statements.setString(1, user.getFirstName());
            statements.setString(2, user.getLastName());
            statements.setString(3, user.getEmail());
            statements.setString(4, user.getPassword());

            System.out.println("Connection Successful!");
            System.out.println("Statement: " + statements);

            result = statements.executeUpdate();
        } catch (SQLException e) {
            // connection fails
            System.out.println("Register Failed");
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        User user = new User("minh", "le", "minhle@bu.edu", "12345");

        UserDao userDao = new UserDao();

        try {
            userDao.insert(user);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
