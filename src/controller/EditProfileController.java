package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import model.UserDatabase;
import model.UserProfile;

/**
 * Created by Allen on 10/2/2016.
 */
public class EditProfileController {
    @FXML TextField profileName;
    @FXML TextField profileAddress;
    @FXML TextField profileEmail;
    @FXML TextField profileContact;
    @FXML Button profileSubmit;
    private Stage profileStage;
    private User user;
    private UserProfile userProfile;
    private UserDatabase database = new UserDatabase();

    /**
     * * sets current stage of this display
     * @param stage stage for this display
     */
    public void setRegistrationStage(Stage stage) {profileStage = stage;}

    /**
     * sets user from login screen
     * @param user user
     */
    public void setUser(User user) throws NullPointerException {
        this.user = user;
        try {
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void handleSubmit(ActionEvent event) throws java.io.IOException {
        if (isValidProfileEdit()) {
            Stage stage = (Stage) profileSubmit.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/UserScreen.fxml"));
            Parent root = fxmlLoader.load();
            UserScreenController controller = fxmlLoader.<UserScreenController>getController();
            controller.setUser(new User(user, userProfile));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * checks if valid ediit profile changes
     * @return true if valid profile edit
     */
    private boolean isValidProfileEdit() {
        String message = "";
        try {
            user = new User(user.getUsername(), profileName.getText(), user.getPassword(), user.getAccountType());
            userProfile = new UserProfile(profileName.getText(), profileEmail.getText(), profileAddress.getText(),
                    profileContact.getText());
            if (database.editUser(user, new User(user, userProfile))) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Edit Profile Success");
                alert.initOwner(profileName.getScene().getWindow());
                alert.setContentText("The user profile has been updated successfully.");
                alert.showAndWait();
                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Edit Profile Error");
                alert.initOwner(profileName.getScene().getWindow());
                alert.setContentText("The user profile could not be edited.");
                alert.showAndWait();
            }
        }
        catch (NullPointerException e) {
            // creates alert window notifying of user not existing in database
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.initOwner(profileName.getScene().getWindow());
            alert.setContentText("You have left some fields blank.");
            alert.showAndWait();
        }
        return false;
    }
}
