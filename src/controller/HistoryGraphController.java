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
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.User;
import model.WaterQualityReport;
import model.WaterSourceReport;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by twalker61 on 10/29/16.
 */
public class HistoryGraphController {

    @FXML CategoryAxis xAxis;
    @FXML NumberAxis yAxis;
    @FXML LineChart historyGraph;
    @FXML Button backButton;
    private ObservableList<WaterQualityReport> qualityReports;
    private ObservableList<WaterSourceReport> sourceReports;
    private User user;
    private MainFXApplication mainApp = new MainFXApplication();

    private int graphYear;

    /**
     * Called automatically to set the legned for the axes
     */
    @FXML
    private void initialize() {
        /*
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
        */
        qualityReports = mainApp.getWaterQualityReports();
        sourceReports = mainApp.getWaterSourceReports();
        setupGraph();
    }

    /**
     * sets the graph year
     */
    public void setYear(int year) {
        graphYear = year;
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

    /**
     * setting up the graph
     */
    @FXML
    private void setupGraph() {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Virus");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Contamination");

        for(int i = 0; i < qualityReports.size(); i++) {
            //if (graphYear == (qualityReports.get(i).getDate().getYear())) {
                series1.getData().add(new XYChart.Data(qualityReports.get(i).getDate().getMonth().toString(), qualityReports.get(i).getContamPPM()));
                series2.getData().add(new XYChart.Data(qualityReports.get(i).getDate().getMonth().toString(), qualityReports.get(i).getVirusPPM()));
            //}
        }
        /*
        for(int i = 0; i < qualityReports.size(); i++) {
            series2.getData().add(new XYChart.Data(qualityReports.get(i).getDate().getMonth().toString(), qualityReports.get(i).getVirusPPM()));
        }*/

        historyGraph.getData().add(series1);
        historyGraph.getData().add(series2);
    }
}
