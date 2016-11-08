package fxapp;



import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.WaterQualityReport;
import model.WaterSourceReport;
import model.User;

import java.io.IOException;

/**
 * Created by Kevin on 9/20/2016.
 */
public class MainFXApplication extends Application {

    public Stage mainScreen;
    private static ObservableList<WaterSourceReport> sourceReports;
    private static ObservableList<WaterQualityReport> qualityReports;
    private static User user;

    /**
     * Runs the whole application
     * @param primaryStage the main container for the application windows
     * @throws Exception unable to start application
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));

        Scene scene = new Scene(root, 400, 275);

        primaryStage.setTitle("Clean Water App: Welcome!");
        primaryStage.setScene(scene);
        mainScreen = primaryStage;
        mainScreen.show();
    }

    /**
     * main method used to run app
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * assigns the system's quality reports
     * @param reports the quality reports
     */
    public void setQualityReports(ObservableList<WaterQualityReport> reports) {
        qualityReports = reports;
    }

    /**
     * assigns the system's source reports
     * @param reports the source reports
     */
    public void setSourceReports(ObservableList<WaterSourceReport> reports) {
        sourceReports = reports;
    }

    /**
     * assigns the system's current user
     * @param user the current user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * returns the list of all quality reports created
     * @return the quality reports
     */
    public ObservableList<WaterQualityReport> getWaterQualityReports() {
        return qualityReports;
    }

    /**
     * returns the list of all source reports created
     * @return the source reports
     */
    public ObservableList<WaterSourceReport> getWaterSourceReports() {
        return sourceReports;
    }

    /**
     * returns the current system user
     * @return the current user
     */
    public User getUser() { return user; }
}
