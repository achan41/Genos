package controller;

import javafx.scene.control.*;
import javafx.stage.Stage;
import model.UserDatabase;
import fxapp.MainFXApplication;
import javafx.event.ActionEvent;
import model.User;
import model.UserDatabase;
import javafx.fxml.FXML;

/**
 * Created by Taiga on 10/1/2016.
 */
public class LoginScreenController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    private Stage loginStage;
    private MainFXApplication mainApp;
    private UserDatabase database = new UserDatabase();


    /**
     * sets current stage of this display
     * @param stage stage for this display
     */
    public void setLoginStage(Stage stage) {loginStage = stage;}

    /**
     * closes window upon cancelling registration
     * @param event
     */
    @FXML
    protected void handleCancelLogin(ActionEvent event) {
        loginStage.close();
    }

    /**
     * handles login event
     * @param event
     */
    @FXML
    protected void handleLogin(ActionEvent event) {
        if (isValidLogin()) {
            mainApp = new MainFXApplication();
            mainApp.showUserScreen();
            loginStage.close();
        }
    }

    /**
     * checks if a user's login is valid
     * @return true if valid login information
     */
    @FXML
    private boolean isValidLogin() {
        String message = "";
        try {
            boolean validLogin = this.database.login(this.usernameField.getText(), this.passwordField.getText());
            if (validLogin) {
                return true;
            } else {
                message = "Your password is incorrect!";
                // creates alert window notifying of incorrect password
                this.passwordField.clear();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.initOwner(loginStage);
                alert.setContentText(message);
                alert.showAndWait();
            }
        }
        catch (NullPointerException e) {
            // creates alert window notifying of user not existing in database
            message = "This user does not exist";
            this.usernameField.clear();
            this.passwordField.clear();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.initOwner(loginStage);
            alert.setContentText(message);
            alert.showAndWait();
        }
        return false;
    }
}
