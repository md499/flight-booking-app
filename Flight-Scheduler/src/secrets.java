/**
 * secrets - a class contains confidential information of the Admin
 * Will be ignored by git
 */
public class secrets {
    /* Attribute ddefinition */
    private static String adEmail = "medha"; // the email of the admin
    private static String adPassword = "cs411"; // the password of the admin

    /**
     * getAdEmail - getter method for getting the email of the admin
     * @return the email of the admin
     */
    public static String getAdEmail() {
        return adEmail;
    }

    /**
     * getAdPassword - getter method for getting the password of the admin
     * @return the password of the admin
     */
    public static String getAdPassword() {
        return adPassword;
    }
}
