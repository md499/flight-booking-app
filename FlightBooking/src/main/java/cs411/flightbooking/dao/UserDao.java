package cs411.flightbooking.dao;

import java.sql.Connection;
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

    private Connection conn;

    public UserDao(String dbName) {
        try {
            this.conn = DBConn.createConnection(dbName);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UserDao() {
        try {
            this.conn = DBConn.defaultConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * insert a newly registered user into the database
     *
     * @param user
     * @return
     * @throws java.lang.ClassNotFoundException
     */
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
        } catch (SQLException e) {
            // connection fails
            System.out.println("Register Failed");
            e.printStackTrace();
        }

        return result;
    }

//    public static void main(String[] args) {
//        User user = new User("min", "le", "min@bu.ed", "12345");
//
//        try {
//            UserDao userdao = new UserDao(Secrets.mysql_url, Secrets.username, Secrets.password);
//            System.out.println("Create userdao success");
//            userdao.insert(user);
//            System.out.println("Register success");
//        } catch (Exception ex) {
//            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
