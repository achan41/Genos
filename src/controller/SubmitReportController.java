package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import model.WaterCondition;
import model.WaterType;

/**
 * Created by dionisiatara on 10/11/16.
 */
public class SubmitReportController {
    @FXML ComboBox<WaterType> waterTypeComboBox;
    @FXML ComboBox<WaterCondition> waterConditionComboBox;
    @FXML TextField location;
    @FXML TextField time;
    @FXML Button cancelButton;
    private User user;

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
     * @param user user
     */
    public void setUser(User user) throws NullPointerException {
        this.user = user;
    }

    /**
     * handles cancel request
     * @param event
     */
    @FXML
    protected void handleCancel(ActionEvent event) throws java.io.IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/UserScreen.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
