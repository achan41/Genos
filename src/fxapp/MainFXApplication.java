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

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));

        Scene scene = new Scene(root, 400, 275);

        primaryStage.setTitle("Clean Water App: Welcome!");
        primaryStage.setScene(scene);
        mainScreen = primaryStage;
        mainScreen.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setQualityReports(ObservableList<WaterQualityReport> reports) {
        qualityReports = reports;
    }
    public void setSourceReports(ObservableList<WaterSourceReport> reports) {
        sourceReports = reports;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public ObservableList<WaterQualityReport> getWaterQualityReports() {
        return qualityReports;
    }
    public ObservableList<WaterSourceReport> getWaterSourceReports() {
        return sourceReports;
    }
    public User getUser() {
        return user;
    }

}
