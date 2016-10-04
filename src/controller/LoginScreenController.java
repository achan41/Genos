package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.UserDatabase;
import fxapp.MainFXApplication;
import javafx.event.ActionEvent;
import model.User;
import model.UserDatabase;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Created by Taiga on 10/1/2016.
 */
public class LoginScreenController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button cancelButton;
    private MainFXApplication mainApp;
    private UserDatabase database = new UserDatabase();


    /**
     * closes window upon cancelling registration
     * @param event
     */
    @FXML
    protected void handleCancelLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));
        Scene scene = new Scene(root, 400, 275);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * handles login event
     * @param event
     */
    @FXML
        if (isValidLogin()) {
        }
    }

    /**
     * checks if a user's login is valid
     * @return true if valid login iinformation
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
            alert.setContentText(message);
            alert.showAndWait();
        }
        return false;
    }
}
