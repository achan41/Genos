package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;
import model.WaterCondition;
import model.WaterType;
import model.WaterReport;
import model.Location;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by dionisiatara on 10/11/16.
 */
public class SubmitReportController {
    @FXML ComboBox<WaterType> waterTypeComboBox;
    @FXML ComboBox<WaterCondition> waterConditionComboBox;
    @FXML TextField reportLocation;
    @FXML TextField reportTime;
    @FXML Button cancelButton;
    @FXML DatePicker date;
    private WaterReport report;
    private ObservableList<String> reports;
    private User user;
    private ArrayList<Location> locations;

    /**
     * called automatically in order to populate the waterTypeComboBox with water types
     * and the waterConditionComboBox with condition types
     */
    @FXML
    private void initialize() {
        ObservableList<WaterType> typeList = FXCollections.observableArrayList(WaterType.values());
        waterTypeComboBox.setItems(typeList);
        ObservableList<WaterCondition> conditionList = FXCollections.observableArrayList(WaterCondition.values());
        waterConditionComboBox.setItems(conditionList);
    }

    /**
     * sets user from login screen
     * @param user current user
     */
    public void setUser(User user) throws NullPointerException {
        this.user = user;
    }

    /**
     * sets report list displayed on userscreen report tab
     * @param reports reports sumbitted so far
     */
    public void setReportsList(ObservableList<String> reports) {
        this.reports = reports;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    /**
     * handles handle choose location for report
     * @param event map controller activated
     */
    @FXML
    protected void handleChooseLocation(ActionEvent event) throws java.io.IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/MapScreen.fxml"));
        Parent root = fxmlLoader.load();
        MapController controller = fxmlLoader.<MapController>getController();

        // Passes on user and report data to user scene, order determines which tab will be active last
        controller.setUser(user);
        controller.setReportsList(reports);
        controller.setLocations(locations);

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
        controller.setReportsList(reports);
        controller.setLocations(locations);

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
            reports.add(report.toString());
            locations.add(report.getLocationObject());

            Stage stage = (Stage) reportTime.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/UserScreen.fxml"));
            Parent root = fxmlLoader.load();
            UserScreenController controller = fxmlLoader.<UserScreenController>getController();

            controller.setUser(user);
            controller.setReportsList(reports);
            controller.setLocations(locations);

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
        String time = reportTime.getText();
        String location = reportLocation.getText();
        WaterCondition condition = waterConditionComboBox.getValue();
        WaterType type = waterTypeComboBox.getValue();
        LocalDate localDate = date.getValue();
        if (localDate == null) {
            errorMessage += "Please select a valid date!\n";
        }
        if (time == null || time.length() == 0 || time.contains("/")) {
            errorMessage += "Please enter a valid time!\n";
        }
        if (location == null || location.length() == 0 || location.contains("/")) {
            errorMessage += "Please enter a valid location!\n";
        }
        if (waterConditionComboBox.getValue() == null) {
            errorMessage += "Please enter a water condition!\n";
        }
        if (waterTypeComboBox.getValue() == null) {
            errorMessage += "Please select a water type.\n";
        }
        if (errorMessage.length() == 0) {
            report = new WaterReport(reports.size() + 1, localDate, time, location, condition, type);
            //System.out.println("In report controller " + locations.size());
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
