package cs411.flightbooking.models;

/**
 * User - a class that represents the user of the system
 *
 */
public class User {

    /* Attributes declaration */
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    /**
     * constructor for the User objects
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     */
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * getEmail - getter method for the email of the user
     *
     * @return the email of the user
     */
    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * setEmail - setter method for setting/changing the email of the user
     *
     * @param newEmail
     */
    public void setEmail(String newEmail) {
        this.email = newEmail;

    }

    /**
     * setPassword - setter method for setting/changing the password of the user
     *
     * @param newPassword
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;

    }

    /**
     * authenticate - method for authenticating user login
     *
     * @param password
     * @return true if the user enter a correct password; otherwise false
     */
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}
