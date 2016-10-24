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
import model.User;
import model.UserDatabase;
import model.Location;
import model.WaterReport;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Taiga on 10/1/2016.
 */
public class UserScreenController {
    @FXML private Label welcomeMessage;
    @FXML Button logoutButton, editProfileButton, submitReportButton, viewMapButton;
    @FXML Label emailLabel, addressLabel, contactLabel;
    @FXML ListView<String> reportListView = new ListView<String>();
    @FXML TabPane tabPane;
    @FXML Tab reportsTab, profileTab;
    @FXML Text reportsCategories;
    private ObservableList<String> reports = FXCollections.observableArrayList();
    private User user;
    private UserDatabase database = new UserDatabase();
    private ArrayList<Location> locations = new ArrayList<Location>();


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
    public void setReportsList(ObservableList<String> reports) {
        this.reports = reports;
        reportListView.setItems(reports);
        changeTab(reportsTab);
    }

    /**
     * Changes tab displayed to main screen tab
     */
    public void setToMainTab() {
        changeTab(profileTab);
    }

    /**
     * sets/updates location list
     * @param locations the list of locations currently submitted
     */
    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
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
        controller.setReportsList(reports);
        controller.setLocations(locations);
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SubmitReportScreen.fxml"));
        Parent root = fxmlLoader.load();
        SubmitReportController controller = fxmlLoader.<SubmitReportController>getController();
        controller.setUser(user);
        controller.setReportsList(reports);
        controller.setLocations(locations);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        controller.setReportsList(reports);
        controller.setLocations(locations);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
