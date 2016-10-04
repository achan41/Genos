package controller;

import fxapp.MainFXApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.UserDatabase;

/**
 * Created by Taiga on 10/1/2016.
 */
public class UserScreenController {
    @FXML private Label welcomeMessage;
    private Stage userScreenStage;
    private MainFXApplication mainApp;
    private UserDatabase database = new UserDatabase();

    /**
     * sets current stage of this display
     * @param stage stage for this display
     */
    public void setUserScreenStage(Stage stage) {userScreenStage = stage;}

    /**
     * handles logout request
     * @param event
     */
    @FXML
    protected void handleLogout(ActionEvent event) {
        userScreenStage.close();
    }

}
