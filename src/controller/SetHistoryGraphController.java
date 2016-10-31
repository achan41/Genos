package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.User;
import model.WaterQualityReport;
import model.WaterSourceReport;

import java.io.IOException;
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
    private ObservableList<WaterSourceReport> sourceReports;
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

    /**
     * set current source reports
     * @param reports
     */
    public void setSourceReportsList(ObservableList<WaterSourceReport> reports) {
        sourceReports = reports;
    }

    /**
     * Submit the data for the history graph and switch to graph screen
     * @param event submit button selected
     * @throws IOException
     */
    @FXML
    protected void handleSubmitGraph(ActionEvent event) throws IOException {
        Stage stage = (Stage) submitButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/HistoryGraphScreen.fxml"));
        Parent root = fxmlLoader.load();
        HistoryGraphController controller = fxmlLoader.getController();
        controller.setUser(user);
        controller.setSourceReportsList(sourceReports);
        controller.setQualityReportsList(qualityReports);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Return to User Screen
     * @param event cancel button selected
     * @throws IOException
     */
    @FXML
    protected void handleCancelButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/UserScreen.fxml"));
        Parent root = fxmlLoader.load();
        UserScreenController controller = fxmlLoader.getController();
        controller.setUser(user);
        controller.setSourceReportsList(sourceReports);
        controller.setQualityReportsList(qualityReports);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
