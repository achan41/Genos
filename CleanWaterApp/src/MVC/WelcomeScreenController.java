package MVC;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * Created by Kevin on 9/20/2016.
 *
 * Controller for the welcome page
 */
public class WelcomeScreenController {
    @FXML
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button registerButton;

    @FXML private Text actionTarget;

    @FXML protected void handleLoginButtonAction(ActionEvent event) {
        //Login functionality, go to app if successful
    }
    @FXML protected void handleRegisterButtonAction(ActionEvent event) {
        //Go to registration page
    }
}
