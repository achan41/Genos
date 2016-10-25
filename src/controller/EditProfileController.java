package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Allen on 10/2/2016.
 */
public class EditProfileController {
    @FXML TextField profileName;
    @FXML TextField profileAddress;
    @FXML TextField profileEmail;
    @FXML TextField profileContact;
    @FXML ComboBox<Title> profileTitle;
    @FXML Button profileSubmit;
    @FXML Button profileCancel;
    private User user;
    private UserProfile userProfile;
    private UserDatabase database = new UserDatabase();
    private ObservableList<WaterSourceReport> sourceReports = FXCollections.observableArrayList();
    private ObservableList<WaterQualityReport> qualityReports = FXCollections.observableArrayList();

    /**
     * called automatically in order to populate the titleBox with Titles
     */
    @FXML
    private void initialize() {
        ObservableList<Title> list = FXCollections.observableArrayList(Title.values());
        profileTitle.setItems(list);
    }

    /**
     * sets user from login screen
     * @param user user
     */
    public void setUser(User user) throws NullPointerException {
        this.user = user;
    }

    /**
     * sets report list displayed on userscreen report tab
     * @param reports reports sumbitted so far
     */
    public void setSourceReportsList(ObservableList<WaterSourceReport> reports) {
        sourceReports = reports;
    }

    /**
     * sets report list displayed on userscreen report tab
     * @param reports reports sumbitted so far
     */
    public void setQualityReportsList(ObservableList<WaterQualityReport> reports) {
        qualityReports = reports;
    }

    /**
     * handles edit profile submission, return to user screen
     * @param event submit profile
     * @throws java.io.IOException cann't access userdatabase
     */
    @FXML
    protected void handleSubmit(ActionEvent event) throws java.io.IOException {
        if (isValidProfileEdit()) {
            swapToUserScreen(new User(user, userProfile));
        }
    }

    /**
     * handles cancel button response, return to user screen
     * @param event cancel submission
     * @throws java.io.IOException
     */
    @FXML
    protected void handleCancel(ActionEvent event) throws java.io.IOException {
        swapToUserScreen(user);
    }

    /**
     * swap to user screen on stage
     * @param user user to pass
     * @throws IOException error accessing database
     */
    @FXML
    private void swapToUserScreen(User user) throws IOException {
        Stage stage = (Stage) profileCancel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/UserScreen.fxml"));
        Parent root = fxmlLoader.load();
        UserScreenController controller = fxmlLoader.<UserScreenController>getController();
        controller.setSourceReportsList(sourceReports);
        controller.setQualityReportsList(qualityReports);
        controller.setUser(user);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * checks if valid edit profile changes
     * @return true if valid profile edit
     */
    private boolean isValidProfileEdit() {
        String errorMessage = "";
        String name = profileName.getText();
        String email = profileEmail.getText();
        String addr = profileAddress.getText();
        String contact = profileContact.getText();
        Title title = profileTitle.getValue();
        if (name == null || name.length() == 0 || name.contains("/")) {
            errorMessage += "Please enter a valid name!\n";
        }
        if (email == null || email.length() == 0 || email.contains("/") || !email.contains("@")) {
            errorMessage += "Please enter a valid email!\n";
        }
        if (addr == null || addr.length() == 0 || addr.contains("/")) {
            errorMessage += "Please enter a valid address!\n";
        }
        if (contact == null || contact.length() == 0 || contact.contains("/")) {
            errorMessage += "Please enter a valid contact number!\n";
        }
        if (profileTitle.getValue() == null) {
            errorMessage += "You have selected an invalid title.\n";
        }
        if (errorMessage.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Profile Change");
            alert.setHeaderText("Please check your profile information!");
            alert.initOwner(profileName.getScene().getWindow());
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        } else {
            try {
                userProfile = new UserProfile(name, email, addr, contact, title);
                user = new User(user, userProfile);
                user.setName(name);
                try {
                    if (database.editUser(user.getUsername(), user)) {
                        sendAlert("INFORMATION", "Edit Profile Success", "The user profile has been updated " +
                                "successfully.");
                        return true;
                    } else {
                        sendAlert("ERROR", "Edit Profile Error", "The user profile could not be saved to database.");
                        return false;
                    }
                } catch (IOException e) {
                    sendAlert("ERROR", "Saving to Database", "The changes could not be saved to database.");
                    return false;
                }
            } catch (NullPointerException e) {
                // creates alert window notifying of user not existing in database
                sendAlert("ERROR", "Invalid Profile Change", "You have left some fields blank.");
                return false;
            }
        }
    }

    /**
     * Sends alert of invalid submission
     * @param type type of alert
     * @param title alert title
     * @param text alert message
     */
    private void sendAlert(String type, String title, String text) {
        Alert alert = new Alert(Alert.AlertType.valueOf(type));
        alert.setTitle(title);
        alert.initOwner(profileName.getScene().getWindow());
        alert.setContentText(text);
        alert.showAndWait();
    }
}
