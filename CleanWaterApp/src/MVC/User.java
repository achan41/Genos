package MVC;

/**
 * Created by Taiga on 9/21/2016.
 */
public class User {
    private String username;
    private String password;

    /**
     * empty constructor
     * not necessary
     */
    public User() {}

    /**
     * constructor creates user with 2 parameters
     * @return
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * returns username
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * returns password
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets user username to be username
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;

    }

    /**
     * sets user username to be password
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return username + " " + password;
    }


}
