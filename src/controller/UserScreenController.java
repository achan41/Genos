package controller;

import fxapp.MainFXApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.User;
import model.UserDatabase;

/**
 * Created by Taiga on 10/1/2016.
 */
public class UserScreenController {
    @FXML private Label welcomeMessage;
    private Stage userScreenStage;
    private MainFXApplication mainApp;
    private UserDatabase database = new UserDatabase();
    private User person;

    /**
     * sets current stage of this display
     * @param stage stage for this display
     */
    public void setUserScreenStage(Stage stage) { userScreenStage = stage; }

    /**
     * setup the main fx application link
     *
     * @param mainFXApplication a link to the MainFXApplication
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApp = mainFXApplication;
    }

    public void setUser(User user) {
        person = user;
    }

    /**
     * handles logout request
     * @param event
     */
    @FXML
    protected void handleLogout(ActionEvent event) {
        //todo return to welcome screen
        userScreenStage.close();
    }

    /**
     * handles edit profile request
     * @param event
     */
    @FXML
    protected void handleEditProfile(ActionEvent event) {
        mainApp.showProfileScreen();
    }
}
