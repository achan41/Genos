package controller;

import fxapp.MainFXApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class WelcomeScreenController {
    private MainFXApplication mainApp;



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
    protected void handleLoginScreenButtonAction(ActionEvent event) {
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
        mainApp.showRegistrationScreen();
        //add user to database
    }
}