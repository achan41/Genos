package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.AccountType;
import model.User;
import model.UserDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

/**
 * Created by Taiga on 10/1/2016.
 */
public class RegistrationScreenController {
    @FXML TextField registrationName;
    @FXML TextField registrationUsername;
    @FXML PasswordField registrationPassword;
    @FXML ComboBox<AccountType> accountTypeBox;
    private UserDatabase database = new UserDatabase();
    private MainFXApplication mainApp;
    private Stage registrationStage;

    /**
     * called automatically in order to populate accountTypeBox with account types
     */
    @FXML
    private void initialize() {
        ObservableList<AccountType> list = FXCollections.observableArrayList(AccountType.values());
        accountTypeBox.setItems(list);
    }

    /**
     * sets current stage of this display
     * @param stage stage for this display
     */
    public void setRegistrationStage(Stage stage) {registrationStage = stage;}

    /**
     * closes window upon cancelling registration
     * @param event
     */
    @FXML
    protected void handleCancelRegistration(ActionEvent event) {
        registrationStage.close();
    }

    /**
     * Reads user input, registers user into database
     * @param event when the button is clicked
     */
    @FXML
    protected void handleRegistration(ActionEvent event) {
        if (isValidUser()) {
            database.addUser(new User(registrationUsername.getText(), registrationName.getText(),
                    registrationPassword.getText(), accountTypeBox.getValue()));
        }
        registrationStage.close();
    }

    /**
     * Checks if user is valid for registration
     * @return true if valid user registration
     */
    @FXML
    private boolean isValidUser() {
        String errorMessage = "";
        if (registrationName.getText() == null || registrationName.getText().length() == 0) {
            errorMessage += "Please enter a name!\n";
        }
        if (registrationUsername.getText() == null || registrationUsername.getText().length() == 0) {
            errorMessage += "Please enter a username!\n";
        }
        String username = registrationUsername.getText();
        if (database.userExists(username)) {
            errorMessage += "A user with this name already exists!\n";
        }
        if (registrationPassword.getText() == null || registrationPassword.getText().length() == 0) {
            errorMessage += "Please enter a password!\n";
        }
        if (accountTypeBox.getValue() == null) {
            errorMessage += "You have selected an invalid account type.\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(registrationStage);
            alert.setTitle("Invalid Registration");
            alert.setHeaderText("Please check your registration");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
