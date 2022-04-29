package cs411.flightbooking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConn - a class for connecting between Java classes and database
 */
public class DBConn {

    private static final String MYSQL_CONF_STRING = "?allowPublicKeyRetrieval=true&useSSL=false";

    /* Configurations for connection with local database */
    private static final String LOCAL_MYSQL_URL = "jdbc:mysql://localhost:3306/";
    private static final String LOCAL_MYSQL_USERNAME = "cs411";
    private static final String LOCAL_MYSQL_PASSWORD = "12345";

    /* Defalt config for connection with AWS database */
    private static final String MYSQL_URL = "jdbc:mysql://cs411.cuybam09krto.us-east-1.rds.amazonaws.com/";
    private static final String MYSQL_USERNAME = "admin";
    private static final String MYSQL_PASSWORD = "12MyAWS34!";

    /**
     * createConnection -
     *
     * @param dbName is the name of the database
     * @return the connection with the specified database's name
     */
    public static Connection createConnection(String dbName)
            throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                MYSQL_URL + dbName + MYSQL_CONF_STRING,
                MYSQL_USERNAME, MYSQL_PASSWORD);
        return conn;
    }

    /**
     * defaultCOnnection
     *
     * @return the connection the the default database named cs411
     * @throws java.lang.ClassNotFoundException
     */
    public static Connection defaultConnection()
            throws ClassNotFoundException, SQLException {
        return createConnection("cs411");
    }
}
