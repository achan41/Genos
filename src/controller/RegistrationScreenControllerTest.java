package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.AccountType;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import static org.junit.Assert.*;
import java.io.IOException;

/**
 * Created by dionisiatara on 11/14/16.
 */
public class RegistrationScreenControllerTest extends GuiTest {
    private FXMLLoader fxmlLoader;
    private RegistrationScreenController controller;
    private TextField name, username, password;
    private ComboBox<AccountType> type;

    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("../view/RegistrationScreen.fxml"));
            parent = fxmlLoader.load();
            return parent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    @Before
    public void setup() {
        controller = fxmlLoader.getController();
        name = find("#registrationName");
        username = find("#registrationUsername");
        password = find("#registrationPassword");
        type = find("#accountTypeBox");
    }

    /**
     * Test methods for {@link RegistrationScreenController#isValidUser()}
     */
    @Test(expected = Exception.class)
    public void validRegistrationTest() {
        // test if all inputs are blanks
        assertEquals(0, name.getText().length());
        assertEquals(0, username.getText().length());
        assertEquals(0, password.getText().length());
        assertNull(type.getValue());
        assertFalse(controller.isValidUser());
    }
}
