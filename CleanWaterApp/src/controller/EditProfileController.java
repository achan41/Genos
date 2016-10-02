package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

/**
 * Created by Allen on 10/2/2016.
 */
public class EditProfileController {
    @FXML TextField profileName;
    @FXML TextField profileAddress;
    @FXML TextField profileEmail;
    @FXML TextField profileContact;
    private Stage profileStage;

    /**
     * * sets current stage of this display
     * @param stage stage for this display
     */
    public void setRegistrationStage(Stage stage) {profileStage = stage;}

    @FXML
    protected void handleSubmit(ActionEvent event) {

    }
}
