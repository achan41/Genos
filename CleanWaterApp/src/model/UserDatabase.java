package model;

import model.User;

import java.util.HashMap;

/**
 * Created by Taiga on 9/21/2016.
 */
public class UserDatabase {

    private User user;
    private String username;
    private HashMap<String, User> database = new HashMap<>();

    /**
     * create userDatabase with user/pass default
     */
    public UserDatabase() {
        database.put("user", new User("user", "pass"));
    }

    /**
     * creates userdatabase with imported data
     * @param database imported database
     */
    public UserDatabase(HashMap<String, User> database) {
        this.database = database;
    }

    /**
     * checks to see if a user exists in the database
     * @param user String username
     * @return boolean value whether user exists in database
     */
    public boolean userExists(String user) {
        return database.containsKey(user);
    }

    /**
     * checks to see if a user exists in the database
     * @param user User object
     * @return boolean value whether user exists in database
     */
    public boolean userExists(User user) {
        return database.containsValue(user);
    }

    /**
     * checks to see if login (user/pass) is valid
     * @param username user's username
     * @param password user's password
     * @return boolean value whether login was valid
     */
    public boolean login(String username, String password) throws NullPointerException {
        User tempUser = database.get(username);
        if (tempUser == null) {
            throw new NullPointerException("This user does not exist");
        }
        if (tempUser.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * appends database to current user database
     * @param database database to be added
     */
    public void addDatabase(HashMap<String, User> database) {
        database.putAll(database);
    }

    /**
     * checks if user database is empty
     * @return boolean whether or not userbase is empty
     */
    public boolean isEmpty() {
        return database.isEmpty();
    }

    /**
     * return database
     * @return database
     */
    public HashMap<String, User> getDatabase() {
        return database;
    }

    /**
     * removes user
     * @param user user to be removed
     * @return boolean whether or not the user was removed
     */
    public boolean removeUser(User user) {
        if (userExists(user)) {
            database.remove(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * removes user
     * @param user user to be removed
     * @return boolean whether or not the user was removed
     */
    public boolean removeUser(String user) {
        if (userExists(user)) {
            database.remove(user);
            return true;
        } else {
            return false;
        }
    }


    /**
     * adds user to database
     * @param user user
     */
    public void addUser(User user) {
        database.put(user.getUsername(), user);
    }
}
