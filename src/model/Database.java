package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Created by Allen on 10/24/2016.
 */
public class Database {
    private Connection connection;
    private PreparedStatement statement;

    /**
     * Attempts to connect to a database
     */
    public Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            String url = "jdbc:mysql://localhost:8808/Test?autoReconnect=true&useSSL=false";
            connection = DriverManager.getConnection (url, "root", "genos");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public boolean addProfile(UserProfile profile) {
        //TODO
        return true;
    }

    public boolean addWaterSourceReport(WaterSourceReport waterSourceReport) {
        //TODO
        return true;
    }

    public boolean addWaterPurityReport(WaterQualityReport waterPurityReport) {
        //TODO
        return true;
    }

    public boolean findUser(String username) {
        //TODO
        return true;
    }

    public boolean removeUser(String username) {
        //TODO
        return true;
    }
}
