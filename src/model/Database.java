package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * Created by Allen on 10/24/2016.
 */
@SuppressWarnings("DefaultFileTemplate")
public class Database {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    private final ObservableList<User> users = FXCollections.observableArrayList();
    private final ObservableList<WaterSourceReport> wsReports = FXCollections.observableArrayList();
    private final ObservableList<WaterQualityReport> wqReports = FXCollections.observableArrayList();

    /**
     * Attempts to connect to a database
     */
    public Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            String url = "jdbc:mysql://108.201.215.73:3306/cleanwater?autoReconnect=true&useSSL=false";
            connection = DriverManager.getConnection (url, "admin", "genos");
            System.out.println("connection set");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(User user) {
        try {
            String query = "SELECT 'username' FROM users WHERE username = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            result = statement.executeQuery();
            if(!(result.next())) {
                query = "INSERT INTO users (id, username, password, name, accountType, userProfile) "
                        + "values (null, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(query);
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getName());
                statement.setString(4, user.getAccountType().toString());
                statement.setString(5, user.getProfile().toString());
                statement.execute();
                users.add(user);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addWaterSourceReport(WaterSourceReport waterSourceReport) {
        try {
            String query = "INSERT INTO watersource (id, time, name, location, overallCondition, type, date, reportNum)"
                    + " values (null, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, waterSourceReport.getTime());
            statement.setString(2, waterSourceReport.getReporterName());
            statement.setString(3, waterSourceReport.getLocation().getLatLongString());
            statement.setString(4, waterSourceReport.getCondition().toString());
            statement.setString(5, waterSourceReport.getType().toString());
            statement.setString(6, waterSourceReport.getDate().toString());
            statement.setDouble(7, waterSourceReport.getReportNum());
            statement.execute();
            wsReports.add(waterSourceReport);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addWaterQualityReport(WaterQualityReport waterPurityReport) {
        try {
            String query = "INSERT INTO waterquality (id, time, name, location, date, "
                    + "reportNum, overallCondition, virusPPM, contaminantPPM)"
                    + " values (null, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, waterPurityReport.getTime());
            statement.setString(2, waterPurityReport.getReporterName());
            statement.setString(3, waterPurityReport.getLocation().getLatLongString());
            statement.setString(4, waterPurityReport.getDate().toString());
            statement.setDouble(5, waterPurityReport.getReportNum());
            statement.setString(6, waterPurityReport.getOverallCondition().toString());
            statement.setDouble(7, waterPurityReport.getVirusPPM());
            statement.setDouble(7, waterPurityReport.getContamPPM());
            statement.execute();
            wqReports.add(waterPurityReport);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean findUser(String username) {
        //TODO
        return true;
    }

    public boolean removeUser(String username) {
        //TODO
        return true;
    }

    public User getUser(String username) {
        for (User person : users) {
            if (person.getUsername().equals(username)) {
                return person;
            }
        }
        return null;
    }

    public ObservableList<User> getUsers() { return users; }
}
