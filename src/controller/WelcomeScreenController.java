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



    /**
     * setup the main fx application link
     *
     * @param mainFXApplication a link to the MainFXApplication
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApp = mainFXApplication;
    }

    /**
     * opens login screen upon button press
     * @param event button press
     */
    @FXML
<<<<<<< HEAD
    protected void handleLoginScreenButtonAction(ActionEvent event) {
        mainApp.showLoginScreen();
=======
    protected void handleLoginScreenButtonAction(ActionEvent e) throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/LoginScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
>>>>>>> origin/master
    }


    /**
     * opens registration screen upon button press
     * @param event
     */
    @FXML
<<<<<<< HEAD
    protected void launchRegisterScreen(ActionEvent event) {
        //tell mainfxapplication to launch register screen
        //to do that, need reference to mainApp passed to controller
        mainApp.showRegistrationScreen();
        //add user to database
=======
    protected void launchRegisterScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/RegistrationScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
>>>>>>> origin/master
    }
}