package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Allen on 9/30/2016.
 */
public class UserProfile {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty number = new SimpleStringProperty();

    public UserProfile(String name) {
        this.setName(name);
        this.setEmail("Edit your profile");
        this.setAddress("Edit your profile");
        this.setNumber("Edit your profile");
    }

    public UserProfile(String name, String email, String address, String number) {
        this.setName(name);
        this.setEmail(email);
        this.setAddress(address);
        this.setNumber(number);
    }

    /**
     * returns user's real name
     * @return user's name
     */
    public String getName() {
        return name.get();
    }

    /**
     * returns the email
     * @return String email
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * returns the address
     * @return String address
     */
    public String getAddress() {
        return address.get();
    }

    /**
     * returns the contact number
     * @return String number
     */
    public String getNumber() { return number.get(); }

    /**
     * sets user profile's name to the name
     * @param name User's name
     */
    private void setName(String name) {
        this.name.set(name);
    }

    /**
     * sets user profile's email to the email
     * @param email User's email
     */
    private void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * sets user profile's address to the address
     * @param address User's address
     */
    private void setAddress(String address) {
        this.address.set(address);
    }

    /**
     * sets user profile's number to be the number
     * @param number User's number
     */
    private void setNumber(String number) {
        this.number.set(number);
    }

    /**
     * returns strong concatenation of user profile
     * @return user data string
     */
    @Override
    public String toString() {
        return email.get() + "/" + address.get() + "/" + number.get();
    }
}


