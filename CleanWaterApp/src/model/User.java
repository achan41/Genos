package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Taiga on 9/21/2016.
 */
public class User {
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final ObjectProperty<AccountType> accountType = new SimpleObjectProperty<>();
    private UserProfile profile;

    /**
     * creates User with 2 parameters
     * @param username user's username
     * @param password user's password
     */
    public User(String username, String password) {
        this.username.set(username);
        this.password.set(password);
    }

    /**
     * returns username
     * @return String username
     */
    public String getUsername() {
        return username.get();
    }

    /**
     * returns password
     * @return String password
     */
    public String getPassword() {
        return password.get();
    }

    /**
     * returns account type of user
     * @return user account type
     */
    public AccountType getAccountType() {return accountType.get();}

    /**
     * sets user username to be username
     * @param username new username
     */
    public void setUsername(String username) {
        this.username.set(username);
    }

    /**
     * sets account type of user
     * @param accountType new account type
     */
    public void setAccountType(AccountType accountType) {
        this.accountType.set(accountType);
    }

    /**
     * sets user username to be password
     * @param password password
     */
    public void setPassword(String password) {
        this.password.set(password);
    }

    /**
     * returns the string concatenation of the user's information
     * @return string
     */
    @Override
    public String toString() {
        return username.get() + " " + password.get() + " " + accountType.get();
    }


    /**
     * returns whether or not the user is a valid user
     * @param user user to be checked
     * @return boolean value whether the user is valid or not
     */
    public boolean isValid(User user) {
        return (user.getUsername() != null && user.getPassword() != null && user.getAccountType() != null);
    }
}
