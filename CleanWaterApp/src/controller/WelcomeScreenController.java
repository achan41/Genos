package controller;

import model.UserDatabase;
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


    @FXML
    protected void handleLoginScreenButtonAction(ActionEvent e) {
        this.setRegisterVisible(false);
        this.setLoginVisible(true);
    }

    @FXML
    protected void handleRegisterScreenButtonAction(ActionEvent e) {
        this.setLoginVisible(false);
        this.setRegisterVisible(true);
    }

    @FXML
    protected void handleLoginButtonAction(ActionEvent event) {
        try {
            boolean validLogin = this.database.login(this.usernameField.getText(), this.passwordField.getText());
            if (validLogin) {
                this.messageText.setText("Successfully logged in, " + this.usernameField.getText() + "!");
            } else {
                this.messageText.setText("Your password is incorrect!");
                this.passwordField.clear();
            }
            this.setLoginVisible(false);
            this.setMainScreenVisible(true);
        }
        catch (NullPointerException e) {
            this.messageText.setText("This user does not exist");
            this.passwordField.clear();
        }
    }

    @FXML
    protected void handleRegisterButtonAction(ActionEvent event) {
    }

    @FXML
    protected void handleCancelButtonAction(ActionEvent event) {
        this.setLoginVisible(false);
        this.setRegisterVisible(false);
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
     * sets register FXML objects to visible boolean
     * @param visible whether or not register form is visible
     */
    @FXML
    protected void setRegisterVisible(boolean visible) {

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