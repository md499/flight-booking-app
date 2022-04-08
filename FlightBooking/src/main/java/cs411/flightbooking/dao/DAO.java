package cs411.flightbooking.dao;

/**
 *
 * DAO - A Data Access Object interface for communicating between the database
 * and Java class T
 *
 * @param <T>
 */
public interface DAO<T> {

    /**
     * insert - a method for insert the information of an object of type T into
     * the database
     *
     * @param object is an object of type T
     * @return an integer representing the result of executing the query
     */
    int insert(T object) throws ClassNotFoundException;

    /**
     * extract - execute a select query and return an object of type T where the
     * primary key is an integer
     *
     * @param primaryKey an integer representing the primary of the object
     * @return the object with the matched primary key from the
     */
}
