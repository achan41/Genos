package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Title;
import model.UserProfile;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import model.User;

import static org.junit.Assert.*;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

/**
 * Created by BurtonGuster on 11/9/16.
 */
public class ValidProfileEditTest extends GuiTest {

    private User user;
    private UserProfile profile;
    private FXMLLoader fxmlLoader;
    private EditProfileController controller;
    private TextField name, email, address, number;
    private ComboBox<Title> title;

    @Override
    protected Parent getRootNode() {
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/EditProfile.fxml"));
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
        name = find("#profileName");
        email = find("#profileEmail");
        address = find("#profileAddress");
        number = find("#profileContact");
    }

    /**
     * Test method for {@link EditProfileController#isValidProfileEdit()}.
     */
    @Test
    public void ValidProfileEditTest() throws IOException {
        //Testing null profile
        profile = new UserProfile(null, null, null, null);
        user = new User(new User(null, null, null, null), profile);

        controller.setUser(user);
        assertNull("#profileName");
        assertNull("#profileEmail");
        assertNull("#profileAddress");
        assertNull("#profileContact");
        assertFalse(controller.isValidProfileEdit());

        //Testing blank profile
        profile = new UserProfile("", "", "", "");
        user = new User(new User("",""), profile);

        controller.setUser(user);
        verifyThat("#profileName", hasText(""));
        verifyThat("#profileEmail", hasText(""));
        verifyThat("#profileAddress", hasText(""));
        verifyThat("#profileContact", hasText(""));
        assertFalse(controller.isValidProfileEdit());

        //Testing null title
        profile = new UserProfile("name", "user@mail.com", "123 Drive Lane", "404/1230987");
        user = new User(new User("user","pass"), profile);

        controller.setUser(user);
        assertNull(profile.getTitle());
        assertNull(title);
        assertFalse(controller.isValidProfileEdit());

        //Testing valid user
        profile = new UserProfile("name", "user@mail.com", "123 Drive Lane", "4041230987", Title.Dr);
        user = new User(new User("user","pass"), profile);

        controller.setUser(user);
        verifyThat("#profileName", hasText("name"));
        verifyThat("#profileEmail", hasText("user@mail.com"));
        verifyThat("#profileAddress", hasText("123 Drive Lane"));
        verifyThat("#profileContact", hasText("4041230987"));
        //Testing alternate Titles
        assertTrue(controller.isValidProfileEdit());
        profile.setTitle(Title.Mr);
        user.setProfile(profile);
        assertTrue(controller.isValidProfileEdit());
        profile.setTitle(Title.Mrs);
        user.setProfile(profile);
        assertTrue(controller.isValidProfileEdit());
        profile.setTitle(Title.Ms);
        user.setProfile(profile);
        assertTrue(controller.isValidProfileEdit());

        //Testing missing @ symbol in email field
        profile = new UserProfile("name", "usermail.com", "123 Drive Lane", "4041230987", Title.Dr);
        user = new User(new User("user","pass"), profile);

        controller.setUser(user);
        verifyThat("#profileName", hasText("name"));
        verifyThat("#profileEmail", hasText("usermail.com"));
        verifyThat("#profileAddress", hasText("123 Drive Lane"));
        verifyThat("#profileContact", hasText("4041230987"));
        assertFalse(controller.isValidProfileEdit());

        //Testing invalid profiles containing "/"
        profile = new UserProfile("na/me", "user@ma/il.com", "123 Dr/ive Lane", "404/1230987", Title.Dr);
        user = new User(new User("us/er","pas/s"), profile);

        controller.setUser(user);
        assertTrue(name.getText().contains("/"));
        assertTrue(email.getText().contains("/"));
        assertTrue(address.getText().contains("/"));
        assertTrue(number.getText().contains("/"));
        assertFalse(controller.isValidProfileEdit());

    }


}
