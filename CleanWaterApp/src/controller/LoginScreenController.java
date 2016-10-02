package controller;

import javafx.stage.Stage;
import model.UserDatabase;
import fxapp.MainFXApplication;
import javafx.event.ActionEvent;
import model.User;
import model.UserDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

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
        String message = "";
        Alert alert;
        try {
            boolean validLogin = this.database.login(this.usernameField.getText(), this.passwordField.getText());
            if (validLogin) {
                message = "Successfully logged in, " + this.usernameField.getText() + "!";
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Message");

            } else {
                message = "Your password is incorrect!";
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                this.passwordField.clear();
            }
        }
        catch (NullPointerException e) {
            message = "This user does not exist";
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            this.passwordField.clear();
        }
        alert.initOwner(loginStage);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
