package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

/**
 * Created by twalker61 on 10/29/16.
 */
public class HistoryGraphController {

    @FXML LineChart historyGraph;
    @FXML CategoryAxis xAxis;
    @FXML NumberAxis yAxis = new NumberAxis(0, 1000, 100);
    @FXML Button backButton;


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
    }

}
