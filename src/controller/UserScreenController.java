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
import model.UserDatabase;
import model.WaterReport;

import java.io.IOException;

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
    private ObservableList<String> reports = FXCollections.observableArrayList();
    private User user;
    private UserDatabase database = new UserDatabase();


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
            SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
            selectionModel.select(profileTab);
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
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(reportsTab);
    }

    /**
     * handles logout request
     * @param event
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
     * handles edit profile
     * @param event
     */
    @FXML
    protected void handleEditProfile(ActionEvent event) throws IOException {
        Stage stage = (Stage) editProfileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/EditProfile.fxml"));
        Parent root = fxmlLoader.load();
        EditProfileController controller = fxmlLoader.<EditProfileController>getController();
        controller.setUser(user);
        controller.setReportsList(reports);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * handles submit a report
     * @param event
     */
    @FXML
    protected void handleSubmitReport(ActionEvent event) throws IOException {
        Stage stage = (Stage) editProfileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SubmitReportScreen.fxml"));
        Parent root = fxmlLoader.load();
        SubmitReportController controller = fxmlLoader.<SubmitReportController>getController();
        controller.setUser(user);
        controller.setReportsList(reports);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handles viewing the map
     * @param event
     * @throws IOException
     */
    @FXML
    protected void handleViewMap(ActionEvent event) throws IOException {
        Stage stage = (Stage) viewMapButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/MapScreen.fxml"));
        MapController controller = fxmlLoader.getController();
        //controller.setUser(user);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
