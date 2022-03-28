import java.util.*;
import java.sql.*;

/**
 * Admin - a classrepresents the Admin of the system
 */
public class Admin extends User {
    /* Constructor for the Amin object */
    public Admin() {
        super(secrets.getAdEmail(), secrets.getAdPassword());
    }
}
