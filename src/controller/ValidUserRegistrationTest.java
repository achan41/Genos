package controller;

import model.AccountType;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.loadui.testfx.GuiTest;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import model.User;
import model.Title;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by BurtonGuster on 11/9/16.
 */
public class ValidUserRegistrationTest extends GuiTest {

    private User user;
    private FXMLLoader fxmlLoader;

    @Override
    protected Parent getRootNode() {

        Parent parent = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("../view/RegistrationScreen.fxml"));
            parent = fxmlLoader.load();
            return parent;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return parent;

    }

    @Before
    public void setup() {
        user = new User(null, "pass");
    }

    /**
     * Test method for {@link RegistrationScreenController#isValidUser()}.
     */
    @Test
    public void setUserTest() {

    }
}
