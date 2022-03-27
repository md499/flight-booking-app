public class User {

    //email 
    //passworivate  

    private String email; 
    private String password;

    public User (String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getPassword () {
    
         return this.password;
    }

    //setter
    public void setEmail(String e) {
        this.email = e;

    }
    public void setPassword(String p) {
        this.password= p;

    }


    public boolean authenticate (String email, String password) {
        return this.email == email && this.password == password;
    }

    
}
