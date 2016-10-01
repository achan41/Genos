package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Taiga on 9/21/2016.
 */
public class User {

    private final StringProperty name = new SimpleStringProperty();
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
        this(username, null, password, null);
    }

    /**
     * creates User
     * @param username user's username
     * @param name user's real name
     * @param password user's password
     * @param accountType user's account type
     */
    public User(String username, String name, String password, AccountType accountType) {
        this.username.set(username);
        this.password.set(password);
        this.name.set(name);
        this.accountType.set(accountType);
    }

    /**
     * returns user's real name
     * @return user's name
     */
    public String getName() {
        return name.get();
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
     * sets user's name to be new name
     * @param name changed name
     */
    public void setName(String name) {
        this.name.set(name);
    }

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

}
