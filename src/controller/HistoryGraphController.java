package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.User;
import model.WaterQualityReport;
import model.WaterSourceReport;

import java.io.IOException;

/**
 * Created by twalker61 on 10/29/16.
 */
public class HistoryGraphController {

    @FXML LineChart historyGraph;
    @FXML CategoryAxis xAxis = new CategoryAxis();
    @FXML NumberAxis yAxis = new NumberAxis(0, 1000, 100);
    @FXML Button backButton;
    private ObservableList<WaterQualityReport> qualityReports;
    private ObservableList<WaterSourceReport> sourceReports;
    private User user;
    private MainFXApplication mainApp = new MainFXApplication();


    /**
     * Called automatically to set the legned for the axes
     */
    @FXML
    private void initialize() {
        ObservableList<String> months = FXCollections.observableArrayList();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        xAxis.setCategories(months);
        qualityReports = mainApp.getWaterQualityReports();
        sourceReports = mainApp.getWaterSourceReports();
    }

    /**
     * sets user from login screen
     * @param user current user
     */
    public void setUser(User user) throws NullPointerException {
        this.user = user;
    }

    /**
     * set current quality reports to be searched for graphing
     * @param reports
     */
    public void setQualityReportsList(ObservableList<WaterQualityReport> reports) {
        qualityReports = reports;
    }

    /**
     * set current source reports
     * @param reports
     */
    public void setSourceReportsList(ObservableList<WaterSourceReport> reports) {
        sourceReports = reports;
    }

    /**
     * Return to the create history graph screen
     * @param event back button selected
     * @throws IOException
     */
    @FXML
    protected void handleBackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SetHistoryGraphScreen.fxml"));
        Parent root = fxmlLoader.load();
        SetHistoryGraphController controller = fxmlLoader.getController();
        controller.setUser(user);
        //controller.setSourceReportsList(sourceReports);
        //controller.setQualityReportsList(qualityReports);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
