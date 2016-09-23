package MVC;


//import controller.WelcomeScreenController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Kevin on 9/20/2016.
 */
public class MainFXApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));

        Scene scene = new Scene(root, 400, 275);

        primaryStage.setTitle("Clean Water App: Welcome!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {launch(args);
    }
}
