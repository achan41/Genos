package controller;

import fxapp.MainFXApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class WelcomeScreenController {
    private MainFXApplication mainApp;


    @FXML
    protected void handleLoginScreenButtonAction(ActionEvent e) {
        mainApp = new MainFXApplication();
        mainApp.showLoginScreen();
    }


    /**
     * opens registration screen upon button press
     * @param event
     */
    @FXML
    protected void launchRegisterScreen(ActionEvent event) {
        //tell mainfxapplication to launch register screen
        //to do that, need reference to mainApp passed to controller
        mainApp = new MainFXApplication();
        mainApp.showRegistrationScreen();
        //add user to database
    }
}