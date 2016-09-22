package MVC;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageText;
    private Text actionTarget;

    private UserDatabase database = new UserDatabase();



    @FXML protected void handleLoginButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        try {
            boolean validLogin = database.login(username, password);
            if (validLogin) {
                messageText.setText("Successfully logged in, " + usernameField.getText() + "!");
            } else {
                messageText.setText("Your password is incorrect!");
                passwordField.clear();
            }
        } catch (NullPointerException e) {
            messageText.setText("This user does not exist");
            passwordField.clear();
        }



    }
    @FXML protected void handleRegisterButtonAction(ActionEvent event) {
        //Go to registration page
    }
}
