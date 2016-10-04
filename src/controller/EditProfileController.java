package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Title;
import model.User;

/**
 * Created by Allen on 10/2/2016.
 */
public class EditProfileController {
    @FXML TextField profileName;
    @FXML TextField profileAddress;
    @FXML TextField profileEmail;
    @FXML TextField profileContact;
    @FXML ComboBox<Title> title;
    private Stage profileStage;
    private User person;
    private MainFXApplication mainApp;

    /**
     * called automatically in order to populate accountTypeBox with account types
     */
    @FXML
    private void initialize() {
        ObservableList<Title> list = FXCollections.observableArrayList(Title.values());
        title.setItems(list);
    }

    public void setMainApp(MainFXApplication mainFXapp) {
        mainApp = mainFXapp;
    }

    public void setUser(User user) {
        person = user;
    }

    /**
     * * sets current stage of this display
     * @param stage stage for this display
     */
    public void setProfileStage(Stage stage) { profileStage = stage; }

    @FXML
    private void handleSubmit(ActionEvent event) {
        if (isValidProfile()) {
            mainApp.getUser().getProfile().setName(profileName.getText());
            mainApp.getUser().getProfile().setEmail(profileEmail.getText());
            mainApp.getUser().getProfile().setAddress(profileAddress.getText());
            mainApp.getUser().getProfile().setNumber(profileContact.getText());
            mainApp.getUser().getProfile().setTitle(title.getValue());
            profileStage.close();
        }
    }

    /**
     * Checks if profile is valid
     * @return true if valid profile
     */
    @FXML
    private boolean isValidProfile() {
        String errorMessage = "";
        //get text from registration form
        String name = profileName.getText();
        String email = profileEmail.getText();
        String address = profileAddress.getText();
        String contact = profileContact.getText();
        if (name == null || name.length() == 0 || name.contains("/")) {
            errorMessage += "Please enter a valid name!\n";
        }
        if (email == null || email.length() == 0 || email.contains("/") || !email.contains("@")) {
            errorMessage += "Please enter a valid email!\n";
        }
        if (address == null || address.length() == 0 || address.contains("/")) {
            errorMessage += "Please enter a valid address!\n";
        }
        if (contact == null || contact.length() == 0 || contact.contains("/")) {
            errorMessage += "Please enter a valid contact number!\n";
        }
        if (title.getValue() == null) {
            errorMessage += "You have selected an invalid title.\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            //send alert warning of registration error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(profileStage);
            alert.setTitle("Invalid Profile");
            alert.setHeaderText("Please check your profile information!");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
