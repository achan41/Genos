package fxapp;



<<<<<<< HEAD
import controller.*;
=======
>>>>>>> origin/master
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

/**
 * Created by Kevin on 9/20/2016.
 */
public class MainFXApplication extends Application {

    private Stage mainScreen;
    private Scene registerScreen;
    private User person;

    public Stage getStage() {
        return mainScreen;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainScreen = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainFXApplication.class.getResource("../view/WelcomeScreen.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 400, 275);

        WelcomeScreenController control = loader.getController();
        control.setMainApp(this);


        primaryStage.setTitle("Clean Water App: Welcome!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {launch(args);
    }

<<<<<<< HEAD
    public void setUser(User user) {
        person = user;
    }

    public User getUser() { return person; }

    /**
     * show registration screen
     */
    public void showRegistrationScreen() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            mainScreen = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/RegistrationScreen.fxml"));
            BorderPane page = loader.load();

            // Set up mainScreen
            mainScreen.setTitle("Registration");
            mainScreen.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(mainScreen);
            Scene scene = new Scene(page);
            mainScreen.setScene(scene);

            // Sets the person into the controller
            RegistrationScreenController controller = loader.getController();
            controller.setMainApp(this);
            controller.setRegistrationStage(mainScreen);

            // Show the dialog and wait until the user closes it
            mainScreen.showAndWait();
            try {
                FXMLLoader loadr = new FXMLLoader();
                loadr.setLocation(MainFXApplication.class.getResource("../view/EditProfile.fxml"));
                BorderPane pane = loadr.load();
                scene = new Scene(pane);
                EditProfileController controller1 = loadr.getController();
                controller1.setProfileStage(mainScreen);
                controller1.setMainApp(this);

                mainScreen.setScene(scene);
                mainScreen.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * shows edit profile screen
     */
    public void showProfileScreen() {
        try {
            //creates new stage linking to EditProfile.fxml
            mainScreen = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/EditProfile.fxml"));
            BorderPane pane = loader.load();

            //creates window properties and links scene to stage
            mainScreen.setTitle("Edit Profile Screen");
            mainScreen.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(pane);
            mainScreen.setScene(scene);


            EditProfileController controller = loader.getController();
            controller.setProfileStage(mainScreen);

            mainScreen.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * shows login screen
     */
    public void showLoginScreen() {
        try {
            //creates new stage linking to loginscreen.fxml
            mainScreen = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/LoginScreen.fxml"));
            VBox pane = loader.load();

            //creates window properties and links scene to stage
            mainScreen.setTitle("Login Screen");
            mainScreen.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(pane);
            mainScreen.setScene(scene);

            //links loginscreencontroller to login screen stage
            LoginScreenController controller = loader.getController();
            controller.setLoginStage(mainScreen);

            mainScreen.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * shows user screen
     */
    public void showUserScreen() {
        try {
            mainScreen = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/UserScreen.fxml"));
            VBox pane = loader.load();

            mainScreen.setTitle("Login Screen");
            mainScreen.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(pane);
            mainScreen.setScene(scene);

            UserScreenController controller = loader.getController();
            controller.setUserScreenStage(mainScreen);

            mainScreen.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
=======
>>>>>>> origin/master
}
