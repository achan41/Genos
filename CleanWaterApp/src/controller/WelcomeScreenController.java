package controller;

import model.User;
import model.UserDatabase;
import fxapp.MainFXApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class WelcomeScreenController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageText, usernameLabel, passwordLabel;
    @FXML private Text actionTarget, welcomeText;
    @FXML private Button cancelButton, loginButton, loginScreenButton, registerScreenButton, logoutButton;
    private String welcomeMSG = "Welcome";
    private UserDatabase database = new UserDatabase();
    private MainFXApplication mainApp;


    /**
     * Initializes controller class in order to listen to database changes
     */
    @FXML
    private void initialize() {
    }

    @FXML
    protected void handleLoginScreenButtonAction(ActionEvent e) {
        this.setLoginVisible(true);
    }

    /**
     * handles login attempts
     * @param event
     */
    @FXML
    protected void handleLoginButtonAction(ActionEvent event) {
        try {
            boolean validLogin = this.database.login(this.usernameField.getText(), this.passwordField.getText());
            if (validLogin) {
                this.messageText.setText("Successfully logged in, " + this.usernameField.getText() + "!");
                this.setLoginVisible(false);
                this.setMainScreenVisible(true);
            } else {
                this.messageText.setText("Your password is incorrect!");
                this.passwordField.clear();
            }
        }
        catch (NullPointerException e) {
            this.messageText.setText("This user does not exist");
            this.passwordField.clear();
        }
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

    /**
     * cancels login attempts
     * @param event
     */
    @FXML
    protected void handleCancelButtonAction(ActionEvent event) {
        this.setLoginVisible(false);
        this.welcomeText.setText(welcomeMSG);
        logoutButton.setVisible(false);
    }

    /**
     * sets login FXML objects to visible boolean
     * @param visible whether or not login form is visible
     */
    @FXML
    protected void setLoginVisible(boolean visible) {
        welcomeText.setText("Login Screen");
        usernameField.clear();
        passwordField.clear();
        usernameLabel.setVisible(visible);
        passwordLabel.setVisible(visible);
        usernameField.setVisible(visible);
        passwordField.setVisible(visible);
        cancelButton.setVisible(visible);
        loginButton.setVisible(visible);
        loginScreenButton.setVisible(!visible);
        registerScreenButton.setVisible(!visible);
        logoutButton.setVisible(!visible);
    }

    /**
     * Modify the view after the login is successful
     * @param visible true if login is successful
     */
    @FXML
    protected void setMainScreenVisible(boolean visible) {
        welcomeText.setVisible(!visible);
        logoutButton.setVisible(visible);
        loginScreenButton.setVisible(!visible);
        registerScreenButton.setVisible((!visible));

    }
    /**
     * Change back to the welcome screen when user logs out
     * @param e when the button is clicked
     */
    @FXML
    protected void handleLogoutButtonAction(ActionEvent e) {
        setMainScreenVisible(false);
        welcomeText.setText(welcomeMSG);
        messageText.setVisible(false);
    }
}