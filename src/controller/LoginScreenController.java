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
    @FXML private Button cancelButton, loginButton;
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
    protected void handleLogin(ActionEvent event) throws IOException {
        if (isValidLogin()) {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/UserScreen.fxml"));
            Parent root = fxmlLoader.load();
            UserScreenController controller = fxmlLoader.<UserScreenController>getController();
            controller.setUser(database.getUser(usernameField.getText()));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
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
            User user = new User(this.usernameField.getText(), this.passwordField.getText());
            boolean validLogin = this.database.login(user);
            if (validLogin) {
                return true;
            } else {
                message = "Your password is incorrect!";
                // creates alert window notifying of incorrect password
                this.passwordField.clear();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.initOwner(cancelButton.getScene().getWindow());
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
            alert.initOwner(cancelButton.getScene().getWindow());
            alert.setContentText(message);
            alert.showAndWait();
        }
        return false;
    }
}
