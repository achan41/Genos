package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.AccountType;
import model.User;
import model.Control;
import model.UserDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

/**
 * Created by Taiga on 10/1/2016.
 */
@SuppressWarnings("DefaultFileTemplate")
public class RegistrationScreenController {
    @FXML TextField registrationName;
    @FXML TextField registrationUsername;
    @FXML PasswordField registrationPassword;
    @FXML ComboBox<AccountType> accountTypeBox;
    @FXML Button cancelButton;
    private final UserDatabase database = new UserDatabase();

    /**
     * called automatically in order to populate accountTypeBox with account types
     */
    @FXML
    private void initialize() {
        ObservableList<AccountType> list = FXCollections.observableArrayList(AccountType.values());
        accountTypeBox.setItems(list);
    }

    /**
     * closes window upon cancelling registration
     * @param event cancel registration
     */
    @FXML
    protected void handleCancelRegistration(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));
        stage.setScene(new Scene(root, 400, 275));
        stage.show();
    }

    /**
     * Reads user input, registers user into database
     * @param event when the button is clicked
     */
    @FXML
    protected void handleRegistration(ActionEvent event) throws IOException {
        if (isValidUser()) {
            database.addUser(new User(registrationUsername.getText(), registrationName.getText(),
                    registrationPassword.getText(), accountTypeBox.getValue()));
            Control.getInstance().addUser(new User(registrationUsername.getText(), registrationName.getText(),
                    registrationPassword.getText(), accountTypeBox.getValue()));
            handleCancelRegistration(event);
        }
    }

    /**
     * Checks if user is valid for registration
     * @return true if valid user registration
     */
    @FXML
    private boolean isValidUser() {
        String errorMessage = "";
        //get text from registration form
        String name = registrationName.getText();
        String username = registrationUsername.getText();
        String password = registrationPassword.getText();
        if (name == null || name.length() == 0 || name.contains("/")) {
            errorMessage += "Please enter a valid name!\n";
        }
        if (username == null || username.length() == 0 || username.contains("/")) {
            errorMessage += "Please enter a valid username!\n";
        }
        try {
            database.userExists(username);
        } catch (Exception e) {
            errorMessage += "A user with this name already exists!\n";
        }
        if (password == null || password.length() == 0 || password.contains("/")) {
            errorMessage += "Please enter a valid password!\n";
        }
        if (accountTypeBox.getValue() == null) {
            errorMessage += "You have selected an invalid account type.\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            //send alert warning of registration error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Registration");
            alert.setHeaderText("Please check your registration");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
