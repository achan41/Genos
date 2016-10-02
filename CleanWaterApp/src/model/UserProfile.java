package model;

/**
 * Created by Allen on 9/30/2016.
 */
public class UserProfile {
    private String name;
    private String email;
    private String address;
    private String number;

    public UserProfile(String name) {
        this.setName(name);
        this.setEmail("Enter your email");
        this.setAddress("Enter your address");
        this.setNumber("Enter your number");
    }

    /**
     * sets user profile's name to the name
     * @param name User's name
     */
    private void setName(String name) {
        this.name = name;
    }

    /**
     * sets user profile's email to the email
     * @param email User's email
     */
    private void setEmail(String email) {
        this.email = email;
    }

    /**
     * sets user profile's address to the address
     * @param address User's address
     */
    private void setAddress(String address) {
        this.address = address;
    }

    /**
     * sets user profile's number to be the number
     * @param number User's number
     */
    private void setNumber(String number) {
        this.number = number;
    }
}


