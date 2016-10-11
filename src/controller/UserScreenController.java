package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.User;
import model.UserDatabase;

import java.io.IOException;

/**
 * Created by Taiga on 10/1/2016.
 */
public class UserScreenController {
    @FXML private Label welcomeMessage;
    @FXML Button logoutButton, editProfileButton;
    @FXML Label emailLabel, addressLabel, contactLabel;
    private User user;
    private UserDatabase database = new UserDatabase();

    /**
     * called automatically in order to populate accountTypeBox with account types
     */
    @FXML
    private void initialize() {
    }

    /**
     * sets user from login screen
     * @param user user
     */
    public void setUser(User user) throws NullPointerException {
        this.user = user;
        try {
            if (user.getUsername() != null || user.getUsername() != "") {
                welcomeMessage.setText("Welcome, " + user.getUsername() + "!");
            }
            emailLabel.setText("Email: " + user.getProfile().getEmail());
            addressLabel.setText("Address: " + user.getProfile().getAddress());
            contactLabel.setText("Contact: " + user.getProfile().getNumber());

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    /**
     * handles logout request
     * @param event
     */
    @FXML
    protected void handleLogout(ActionEvent event) throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));
        Scene scene = new Scene(root, 400, 275);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * handles edit profile
     * @param event
     */
    @FXML
    protected void handleEditProfile(ActionEvent event) throws IOException {
        Stage stage = (Stage) editProfileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/EditProfile.fxml"));
        Parent root = fxmlLoader.load();
        EditProfileController controller = fxmlLoader.<EditProfileController>getController();
        controller.setUser(user);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
