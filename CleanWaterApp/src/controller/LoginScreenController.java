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
    private UserDatabase database;


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

    @FXML
    protected void handleLogin(ActionEvent event) {

    }

    /**
     * handles login attempts
     * @param event
     */
    /**
    @FXML
    protected void handleLoginButtonAction(ActionEvent event) {
        try {
            boolean validLogin = this.database.login(this.usernameField.getText(), this.passwordField.getText());
            if (validLogin) {
                this.messageText.setText("Successfully logged in, " + this.usernameField.getText() + "!");
                this.setLoginVisible(false);
                this.setMainScreenVisible(true);
            } else {
                this.messageText.setText("Your password is incorrect!");
                this.passwordField.clear();
            }
        }
        catch (NullPointerException e) {
            this.messageText.setText("This user does not exist");
            this.passwordField.clear();
        }
    }
    **/
}
