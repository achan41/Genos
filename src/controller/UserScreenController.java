package controller;

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

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Taiga on 10/1/2016.
 */
public class UserScreenController {
    @FXML private Label welcomeMessage;
    @FXML Button logoutButton, editProfileButton, submitReportButton, viewMapButton, historyGraphButton;
    @FXML Label emailLabel, addressLabel, contactLabel;
    @FXML ListView<String> reportListView = new ListView<String>();
    @FXML TabPane tabPane;
    @FXML Tab reportsTab, profileTab;
    @FXML Text reportsCategories;
    private ObservableList<WaterSourceReport> sourceReports = FXCollections.observableArrayList();
    private ObservableList<WaterQualityReport> qualityReports = FXCollections.observableArrayList();
    private ObservableList<String> reportStrings = FXCollections.observableArrayList();
    private User user;

    @FXML
    private void initialize() {
    }

    /**
     * sets user from login screen
     * @param user user
     */
    public void setUser(User user) throws NullPointerException {
        this.user = user;
        try {
            if (user.getProfile().getName() != null && user.getProfile().getTitle() != null && user.getName() != null) {
                welcomeMessage.setText("Welcome, " + user.getProfile().getTitle().toString()
                        + ". " + user.getName() + "!");
            } else if (user.getProfile().getName() != null) {
                welcomeMessage.setText("Welcome, " + user.getProfile().getName() + "!");
            }  else if (user.getName() != null) {
                welcomeMessage.setText("Welcome, " + user.getName() + "!");
            } else if (user.getUsername() != null) {
                welcomeMessage.setText("Welcome, " + user.getUsername() + "!");
            }
            emailLabel.setText("Email: " + user.getProfile().getEmail());
            addressLabel.setText("Address: " + user.getProfile().getAddress());
            contactLabel.setText("Contact: " + user.getProfile().getNumber());
            changeTab(profileTab);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * sets reports from observablelist
     * @param reports to be added
     */
    @FXML
    public void setSourceReportsList(ObservableList<WaterSourceReport> reports) {
        sourceReports = reports;
        for (WaterSourceReport report : sourceReports) {
            reportStrings.add(report.toString());
        }
        reportListView.setItems(reportStrings);
        changeTab(reportsTab);
    }

    /**
     * sets reports from observablelist
     * @param reports to be added
     */
    @FXML
    public void setQualityReportsList(ObservableList<WaterQualityReport> reports) {
        qualityReports = reports;
        for (WaterQualityReport report : qualityReports) {
            reportStrings.add(report.toString());
        }
        reportListView.setItems(reportStrings);
        changeTab(reportsTab);
    }

    /**
     * Changes tab displayed to main screen tab
     */
    public void setToMainTab() {
        changeTab(profileTab);
    }

    /**
     * change tab of user screen tabpane
     * @param tab tab to make active
     */
    @FXML
    private void changeTab(Tab tab) {
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab);
    }

    /**
     * handles logout request - return to welcome screen
     * @param event logout
     */
    @FXML
    protected void handleLogout(ActionEvent event) throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));
        Scene scene = new Scene(root, 400, 275);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * handles edit profile - switch to edit profile screen
     * @param event edit profile selected
     */
    @FXML
    protected void handleEditProfile(ActionEvent event) throws IOException {
        Stage stage = (Stage) editProfileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/EditProfile.fxml"));
        Parent root = fxmlLoader.load();
        EditProfileController controller = fxmlLoader.<EditProfileController>getController();
        controller.setUser(user);
        controller.setSourceReportsList(sourceReports);
        controller.setQualityReportsList(qualityReports);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * handles submit a report - switch to report submission screen
     * @param event submit report selected
     */
    @FXML
    protected void handleSubmitReport(ActionEvent event) throws IOException {
        Stage stage = (Stage) editProfileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SubmitSourceScreen.fxml"));
        Parent root = fxmlLoader.load();
        SubmitSourceController controller = fxmlLoader.<SubmitSourceController>getController();
        controller.setUser(user);
        controller.setSourceReportsList(sourceReports);
        controller.setQualityReportsList(qualityReports);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * handles submit a quality report - switches to submit quality repotr screen
     * @param event submit report selected
     */
    @FXML
    protected void handleSubmitQuality(ActionEvent event) throws IOException {
        if (!user.getAccountType().equals(AccountType.Worker) && !user.getAccountType().equals(AccountType.Manager)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Account Type");
            alert.setHeaderText("Please check your account type");
            alert.setContentText("You do not have the valid privileges to access the quality report submission screen.");
            alert.showAndWait();
        } else {
            Stage stage = (Stage) editProfileButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SubmitQualityScreen.fxml"));
            Parent root = fxmlLoader.load();
            SubmitQualityController controller = fxmlLoader.<SubmitQualityController>getController();
            controller.setUser(user);
            controller.setSourceReportsList(sourceReports);
            controller.setQualityReportsList(qualityReports);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
     * Switches to history graph creation screen
     * @param event view history graph button selected
     */
    @FXML
    protected void handleQualityHistory(ActionEvent event) throws IOException {
        if (!user.getAccountType().equals(AccountType.Worker) && !user.getAccountType().equals(AccountType.Manager)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Account Type");
            alert.setHeaderText("Please check your account type");
            alert.setContentText("You do not have the valid privileges to access the quality report submission screen.");
            alert.showAndWait();
        } else {
            Stage stage = (Stage) editProfileButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SetHistoryGraphScreen.fxml"));
            Parent root = fxmlLoader.load();
            SetHistoryGraphController controller = fxmlLoader.<SetHistoryGraphController>getController();
            controller.setUser(user);
            controller.setQualityReportsList(qualityReports);
            controller.setSourceReportsList(sourceReports);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Handles viewing the map - switch to map scene
     * @param event View Map selected
     * @throws IOException
     */
    @FXML
    protected void handleViewMap(ActionEvent event) throws IOException {
        Stage stage = (Stage) viewMapButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/MapScreen.fxml"));
        Parent root = fxmlLoader.load();
        MapController controller = fxmlLoader.<MapController>getController();
        controller.setUser(user);
        controller.setSourceReportsList(sourceReports);
        controller.setQualityReportsList(qualityReports);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
