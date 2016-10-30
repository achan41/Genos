package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import model.User;
import model.WaterQualityReport;

import java.util.Set;
import java.util.HashSet;

/**
 * Created by twalker61 on 10/29/16.
 */
public class SetHistoryGraphController {

    @FXML ListView locationList;
    @FXML RadioButton virusButton;
    @FXML RadioButton contamButton;
    @FXML TextField graphYear;
    @FXML Button cancelButton;
    @FXML Button submitButton;
    private ObservableList<WaterQualityReport> qualityReports;
    private User user;

    /**
     * called automatically in order to populate the location list view with available locations
     */
    @FXML
    private void initialize() {
        Set<String> temp = new HashSet<String>();
        for (WaterQualityReport r : qualityReports) {
            temp.add(r.getLocation().getCity() + r.getLocation().getState()
                + r.getLocation().getCountry());
        }
        ObservableList<String> locations = FXCollections.observableArrayList(temp);
        locationList.setItems(locations);
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


}
