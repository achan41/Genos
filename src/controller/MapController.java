package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Location;
import javafx.collections.ObservableList;
import model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.WaterReport;
import netscape.javascript.JSObject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import model.User;

/**
 * Created by Kevin on 10/17/2016.
 */
public class MapController implements Initializable, MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;
    private GoogleMap map;

    private boolean chooseLoc = false;
    private User user;
    private WaterReport report;
    private ObservableList<WaterReport> reports;
    private ArrayList<Location> sourceLocations;

    @FXML
    Button exitMapViewButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }
    
    /**
     * sets user from login screen
     * @param user current user
     */
    public void setUser(User user) throws NullPointerException {
        this.user = user;
    }

    /**
     * sets reports from observablelist
     * @param reports to be added
     */
    @FXML
    public void setReportsList(ObservableList<WaterReport> reports) {
        this.reports = reports;
    }

    @FXML
    public void setReport(WaterReport report) {this.report = report;}

    /**
     * sets whether or not loc should be chosen
     * @param chooseLoc whether or not loc should be chosen
     */
    public void setChooseLoc(boolean chooseLoc) {
        this.chooseLoc = chooseLoc;
    }

    /**
     * sets location list
     * @param locations list of locations submitted so far
     */
    public void setLocations(ArrayList<Location> locations) {
        sourceLocations = locations;
    }

    /**
     * Set map properties, display map, obtain locations of reports, and display report markers on map
     */
    public void mapInitialized() {
        MapOptions options = new MapOptions();

        LatLong center = new LatLong(34, -84);

        options.center(center)
                .zoom(7)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);

        map = mapView.createMap(options);

        for (WaterReport report : reports) {
            MarkerOptions markerOptions = new MarkerOptions();
            Location l = report.getLocation();
            LatLong loc = new LatLong(l.getLat(), l.getLong());

            markerOptions.position(loc)
                    .visible(Boolean.TRUE)
                    .title(l.getName());

            Marker marker = new Marker(markerOptions);

            map.addUIEventHandler(marker,
                    UIEventType.click,
                    (JSObject obj) -> {
                        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                        infoWindowOptions.content(
                                "<h3>" + l.getName() + "</h3>" + "\n"
                                + l.getDescription() + "<br />"
                                + l.getLatLongString() + "<br />"
                                + report.getCondition() + " " + report.getType() + "<br />"
                                + report.getDate() + " " + report.getTime()

                        );

                        InfoWindow window = new InfoWindow(infoWindowOptions);
                        window.open(map, marker);
                    });

            map.addMarker(marker);
        }
        //if at map screen in order to choose a location as opposed to simply viewing it
        if (chooseLoc) {
            map.addUIEventHandler(map, UIEventType.click, (JSObject obj) -> {
                LatLong latLong = new LatLong((JSObject) obj.getMember("latLng"));
                //alert user to confirm location selection
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Location Confirmation");
                alert.setContentText("Are you sure you want to choose this location?");
                alert.showAndWait().ifPresent((response -> {
                    if (response == ButtonType.OK) {
                        try {
                            //go back to submit report screen and preserve location selected
                            Stage stage = (Stage) exitMapViewButton.getScene().getWindow();
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SubmitReportScreen.fxml"));
                            Parent root = fxmlLoader.load();
                            SubmitReportController controller = fxmlLoader.<SubmitReportController>getController();
                            controller.setUser(user);
                            controller.setReportsList(reports);
                            controller.setReport(report);
                            controller.setLocations(sourceLocations);
                            controller.setCurrentLocation(latLong);
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            //catch io exception for fxmlLoader
                        }
                    }
                }));

            });
        }
    }

    /**
     * Handles exiting the map
     * @param event exit the map
     * @throws IOException
     */
    @FXML
    protected void handleMapExit(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitMapViewButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/UserScreen.fxml"));
        Parent root = fxmlLoader.load();
        UserScreenController controller = fxmlLoader.<UserScreenController>getController();
        controller.setUser(user);
        controller.setReportsList(reports);
        controller.setToMainTab();
        controller.setLocations(sourceLocations);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
