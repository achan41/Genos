package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

/**
 * Created by BurtonGuster on 11/9/16.
 */
public class ValidProfileEditTest extends GuiTest {

    private User user;
    private FXMLLoader fxmlLoader;
    private RegistrationScreenController controller;

    @Override
    protected Parent getRootNode() {
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/RegistrationScreen.fxml"));
            root = fxmlLoader.load();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;

    }

    @Before
    public void setup() {
        controller = fxmlLoader.getController();
    }

    /**
     * Test method for {@link RegistrationScreenController#isValidUser()}.
     */
    @Test
    public void EmptyUserTest() throws IOException {
        TextField name = find("#registrationName");
        name.setText("");
        verifyThat("#registrationName", hasText(""));

        TextField username = find("#registrationUsername");
        username.setText("");
        verifyThat("#registrationUsername", hasText(""));

        PasswordField password = find("#registrationPassword");
        password.setText("");
        verifyThat("#registrationPassword", hasText(""));

        user = new User("","","",null);
        testRegistration(user);
    }

    public void testRegistration(User user) throws IOException {
        controller.handleRegistration(new ActionEvent(null, null));
    }
}
