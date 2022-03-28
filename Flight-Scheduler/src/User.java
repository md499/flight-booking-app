/**
 * User - a class that represents the user of the system
 * 
 */
public class User {
    /* Attributes declaration */
    private String email;
    private String password;

    /**
     * constructor for the User objects
     * 
     * @param email
     * @param password
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * getEmail - getter method for the email of the user
     * 
     * @return the email of the user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * getPassword - getter method for the password of the user
     * 
     * @return the password of the user
     */
    public String getPassword() {

        return this.password;
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
        return this.password == password;
    }
}
