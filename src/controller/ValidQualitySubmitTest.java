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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by BurtonGuster on 11/9/16.
 */
public class ValidQualitySubmitTest extends GuiTest {

    private User user;
    private FXMLLoader fxmlLoader;

    @Override
    protected Parent getRootNode() {
        Parent root = null;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("../view/SubmitQualityScreen.fxml"));
            root = fxmlLoader.load();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    @Before
    public void setup() {
        user = new User(null, "pass");
    }

    /**
     * Test method for {@link SubmitQualityController#isValidSubmit()}.
     */
    @Test
    public void setUserTest() {

    }
}
