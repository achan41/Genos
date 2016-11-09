package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.User;
import model.Control;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Created by Taiga on 10/1/2016.
 */
@SuppressWarnings("DefaultFileTemplate")
public class LoginScreenController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button cancelButton, loginButton;


    /**
     * closes window upon cancelling registration
     * @param event cancel login
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
     * @param event select login
     */
    @FXML
    protected void handleLogin(ActionEvent event) throws IOException {
        if (isValidLogin()) {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/UserScreen.fxml"));
            Parent root = fxmlLoader.load();
            UserScreenController controller = fxmlLoader.getController();
            controller.setUser(Control.getInstance().getDatabase().getUser(usernameField.getText()));
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
        String message;
        try {
            User user = new User(this.usernameField.getText(), this.passwordField.getText());
            if (Control.getInstance().getDatabase().findUser(user)) {
                return true;
            } else {
                message = "Your password is incorrect!";
                // creates alert window notifying of incorrect password
                this.passwordField.clear();
                sendAlert("ERROR","Login Error", message);
            }
        }
        catch (NullPointerException e) {
            // creates alert window notifying of user not existing in database
            message = "This user does not exist";
            this.usernameField.clear();
            this.passwordField.clear();
            sendAlert("ERROR","Login Error", message);
        }
        return false;
    }

    /**
     * sends alert to user in this screen
     * @param type type of alert
     * @param title alert title
     * @param message message to send
     */
    @FXML
    private void sendAlert(String type, String title, String message) {
        Alert alert = new Alert(Alert.AlertType.valueOf(type));
        alert.setTitle(title);
        alert.initOwner(loginButton.getScene().getWindow());
        alert.setContentText(message);
        alert.showAndWait();
    }
}
