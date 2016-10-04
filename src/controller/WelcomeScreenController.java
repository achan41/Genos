package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class WelcomeScreenController {
    @FXML private Button loginButton, registerButton;


    @FXML
    protected void handleLoginScreenButtonAction(ActionEvent e) throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/LoginScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * opens registration screen upon button press
     * @param event
     */
    @FXML
    protected void launchRegisterScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/RegistrationScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}