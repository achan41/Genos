package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.AccountType;
import org.loadui.testfx.GuiTest;

import java.io.IOException;

/**
 * Created by dionisiatara on 11/14/16.
 */
public class RegistrationScreenControllerTest extends GuiTest {
    private FXMLLoader fxmlLoader;
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
}
