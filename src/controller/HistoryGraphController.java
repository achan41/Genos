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

import java.io.IOException;

/**
 * Created by twalker61 on 10/29/16.
 */
@SuppressWarnings("DefaultFileTemplate")
public class HistoryGraphController {

    @FXML CategoryAxis xAxis;
    @FXML NumberAxis yAxis;
    @FXML LineChart historyGraph;
    @FXML Button backButton;
    private ObservableList<WaterQualityReport> qualityReports;
    private User user;
    private final MainFXApplication mainApp = new MainFXApplication();

    private int graphYear;
    private boolean virus = false;
    private boolean contaminant = false;
    private String location;

    /**
     * Called automatically to set the legend for the axes
     */
    @FXML
    private void initialize() {
        // not working yet
        /*
]        ObservableList<String> months = FXCollections.observableArrayList();
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
        setupGraph();
    }

    /**
     * sets the graph year
     */
    public void setYear(int year) {
        graphYear = year;
    }

    /**
     * sets virus to be represented
     */
    public void setVirus() {
        virus = true;
    }

    /**
     * sets contaminant to be represented
     */
    public void setContam() {
        contaminant = true;
    }

    /**
     * sets location of the graph
     * @param location the location of the graph
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * sets user from login screen
     * @param user current user
     */
    public void setUser(User user) throws NullPointerException {
        this.user = user;
    }

    /**
     * Return to the create history graph screen
     * @param event back button selected
     * @throws IOException problem with receiving back command
     */
    @FXML
    protected void handleBackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SetHistoryGraphScreen.fxml"));
        Parent root = fxmlLoader.load();
        SetHistoryGraphController controller = fxmlLoader.getController();
        controller.setUser(user);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * setting up the graph
     */
    @FXML
    void setupGraph() {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Virus");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Contamination");

        for (WaterQualityReport qualityReport : qualityReports) {
            if (graphYear == (qualityReport.getDate().getYear()) && location.equals(qualityReports.get(0).getLocation().toString())) {
                if (virus) {
                    try {
                        series1.getData().add(new XYChart.Data(qualityReport.getDate().getMonth().toString(), qualityReport.getContamPPM()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (contaminant) {
                    try {
                        series2.getData().add(new XYChart.Data(qualityReport.getDate().getMonth().toString(), qualityReport.getVirusPPM()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            historyGraph.getData().add(series1);
            historyGraph.getData().add(series2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
