package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.AccountType;
import model.UserDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.ComboBox;
import model.UserProfile;

/**
 * Created by Taiga on 10/1/2016.
 */
public class RegistrationScreenController {
    @FXML TextField registrationName;
    @FXML TextField registrationUsername;
    @FXML PasswordField registrationPassword;
    @FXML ComboBox<AccountType> accountTypeBox;
    private UserDatabase database = new UserDatabase();
    private UserProfile profile;
    private MainFXApplication mainApp;
    private Stage registrationStage;

    /**
     * called automatically in order to populate accountTypeBox with account types
     */
    @FXML
    private void initialize() {
        ObservableList<AccountType> list = FXCollections.observableArrayList(AccountType.values());
        accountTypeBox.setItems(list);
    }

    /**
     * sets current stage of this display
     * @param stage stage for this display
     */
    public void setRegistrationStage(Stage stage) {registrationStage = stage;}

    /**
     * handles registration cancellation
     * @param event
     */
    @FXML
    protected void handleCancelRegistration(ActionEvent event) {
        registrationStage.close();
    }

    /**
     * Reads user input, registers user into database
     * @param event when the button is clicked
     */
    @FXML
    protected void handleRegistration(ActionEvent event) {

    }
}
