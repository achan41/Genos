package fxapp;



import controller.LoginScreenController;
import controller.RegistrationScreenController;
import controller.UserScreenController;
import controller.WelcomeScreenController;
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

import java.io.IOException;

/**
 * Created by Kevin on 9/20/2016.
 */
public class MainFXApplication extends Application {

    public Stage mainScreen;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));

        Scene scene = new Scene(root, 400, 275);

        primaryStage.setTitle("Clean Water App: Welcome!");
        primaryStage.setScene(scene);
        mainScreen = primaryStage;
        mainScreen.show();
    }

    public static void main(String[] args) {launch(args);
    }

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
            controller.setRegistrationStage(mainScreen);

            // Show the dialog and wait until the user closes it
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
}
