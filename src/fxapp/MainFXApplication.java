package fxapp;



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

}
