package controller;

import com.lynden.gmapsfx.javascript.object.LatLong;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by taiga on 10/24/2016.
 */
public class SubmitQualityController {
    @FXML ComboBox<OverallCondition> overallComboBox;
    @FXML TextField reporterName, virusPPMField, contamPPMField, reportTime;
    @FXML DatePicker date;
    @FXML Button cancelButton, submitButton;
    @FXML String textLocation;
    //@FXML Text locationText;
    private WaterQualityReport report;
    private ObservableList<WaterSourceReport> sourceReports;
    private ObservableList<WaterQualityReport> qualityReports;
    private User user;
    private LatLong latLong;

    /**
     * called automatically in order to populate the waterTypeComboBox with water types
     * and the waterConditionComboBox with condition types
     */
    @FXML
    private void initialize() {
        ObservableList<OverallCondition> overallList = FXCollections.observableArrayList(OverallCondition.values());
        overallComboBox.setItems(overallList);
    }

    /**
     * sets user from login screen
     * @param user current user
     */
    public void setUser(User user) throws NullPointerException {
        this.user = user;
        reporterName.setText(user.getName());
    }

    /**
     * sets submit fields to water report's data
     * @param report report to pull data from
     */
    public void setReport(WaterQualityReport report) {
        date.setValue(report.getDate());
        reportTime.setText(report.getTime());
        reporterName.setText(report.getLocation().getName());
        overallComboBox.setValue(report.getOverallCondition());
    }

    /**
     * sets report list displayed on userscreen report tab
     * @param reports reports sumbitted so far
     */
    public void setSourceReportsList(ObservableList<WaterSourceReport> reports) {
        sourceReports = reports;
    }

    /**
     * sets report list displayed on userscreen report tab
     * @param reports reports sumbitted so far
     */
    public void setQualityReportsList(ObservableList<WaterQualityReport> reports) {
        qualityReports = reports;
    }

    /**
     * sets water report location to be chosen location
     * @param latLong latitude/longitude to set current report to
     */

    public void setCurrentLocation(LatLong latLong) {
        String locText = "";
        this.latLong = latLong;
        if (latLong.getLatitude() > 0) {
            locText += latLong.getLatitude() + "*N ";
        } else {
            locText += latLong.getLatitude() + "*S ";
        }
        if (latLong.getLongitude() > 0) {
            locText += latLong.getLongitude() + "*E";
        } else {
            locText += latLong.getLongitude() + "*W";
        }
        //locationText.setText(locText);
    }

    /**
     * handles choosing of location
     * @param event
     * @throws java.io.IOException
     */
    @FXML
    protected void handleChooseLocation(ActionEvent event) throws java.io.IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/MapScreen.fxml"));
        Parent root = fxmlLoader.load();
        MapController controller = fxmlLoader.<MapController>getController();

        // Passes on user and report data to user scene, order determines which tab will be active last
        controller.setUser(user);
        controller.setQualityReportsList(qualityReports);
        controller.setSourceReportsList(sourceReports);
        Location tempLocation = new Location("", "", false);
        if (reporterName.getText() != null) {
            tempLocation.setName(reporterName.getText());
        }
        WaterQualityReport report = new WaterQualityReport(
                qualityReports.size() + 1,
                reporterName.getText(),
                date.getValue(),
                reportTime.getText(),
                tempLocation,
                overallComboBox.getValue(),
                virusPPMField.getText(),
                contamPPMField.getText());
        controller.setQualityReport(report);
        controller.setChooseLoc(true);
        //controller.setReportType("quality");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * handles cancel request, returns to user screen
     * @param event cancel report submission
     */
    @FXML
    protected void handleCancel(ActionEvent event) throws java.io.IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/UserScreen.fxml"));
        Parent root = fxmlLoader.load();
        UserScreenController controller = fxmlLoader.<UserScreenController>getController();

        // Passes on user and report data to user scene, order determines which tab will be active last
        controller.setUser(user);
        controller.setSourceReportsList(sourceReports);
        controller.setQualityReportsList(qualityReports);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * reads user water report input, adds report to report list
     * @param event when the button is pressed
     * @throws java.io.IOException unable to access database
     */
    @FXML
    protected void handleSubmit(ActionEvent event) throws java.io.IOException {
        if (isValidSubmit()) {
            qualityReports.add(report);

            Stage stage = (Stage) reportTime.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/UserScreen.fxml"));
            Parent root = fxmlLoader.load();
            UserScreenController controller = fxmlLoader.<UserScreenController>getController();

            controller.setUser(user);
            controller.setSourceReportsList(sourceReports);
            controller.setQualityReportsList(qualityReports);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * checks if water report is valid
     * @return true if valid water report submit
     */
    @FXML
    private boolean isValidSubmit() {
        String errorMessage = "";
        //get text from registration form
        String name = reporterName.getText();
        String time = reportTime.getText();
        OverallCondition condition = overallComboBox.getValue();
        LocalDate localDate = date.getValue();
        String virusPPM = virusPPMField.getText();
        String contamPPM = contamPPMField.getText();
        Location location = new Location(name, "", latLong);
        if (name == null) {
            errorMessage += "Please enter your name!\n";
        }
        if (localDate == null) {
            errorMessage += "Please select a valid date!\n";
        }
        if (time == null || time.length() == 0 || time.contains("/")) {
            errorMessage += "Please enter a valid time!\n";
        }
        if (location == null) {
            errorMessage += "Please enter a valid location!\n";
        }
        if (overallComboBox.getValue() == null) {
            errorMessage += "Please select a water type.\n";
        }
        if (errorMessage.length() == 0) {
            report = new WaterQualityReport(qualityReports.size() + 1, name, localDate, time, location, condition, virusPPM, contamPPM);
            return true;
        } else {
            //send alert warning of registration error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Water Report Submission");
            alert.setHeaderText("Please check your water report");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
