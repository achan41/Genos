package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.lynden.gmapsfx.javascript.object.LatLong;
import java.sql.*;
import java.time.LocalDate;

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

    public boolean findUser(User user) {
        for (User person : users) {
            if (person.getUsername().equals(user.getName())) {
                if (person.getPassword().equals(user.getPassword())) {
                    return true;
                }
            }
        }
        return false;
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

    public void restoreUsers() {
        users.removeAll();
        String query = "SELECT * FROM users";

        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            while(result.next()) {
                String username = result.getString("username");
                String password = result.getString("password");
                String name = result.getString("name");
                AccountType accountType = AccountType.valueOf(result.getString("accountType"));
                String profile = result.getString("userProfile");
                String array[] = profile.split("/");
                UserProfile userProfile = new UserProfile(array[0], array[1], array[2], array[3]);
                User user = new User(username, password, name, accountType);
                User uUser = new User(user, userProfile);
                users.add(uUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void restoreWsReports() {
        wsReports.removeAll();
        String query = "SELECT * FROM watersource";

        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            while(result.next()) {
                String time = result.getString("time");
                String nameOfReporter = result.getString("name");
                LocalDate date = LocalDate.parse(result.getString("date"));
                WaterCondition overallCondition = WaterCondition.valueOf(result.getString("overallCondition"));
                WaterType type = WaterType.valueOf(result.getString("type"));
                String latlong = result.getString("location");
                latlong = latlong.replaceAll("[^0-9.]", "");
                String array[] = latlong.split("\\s+");
                Location location = new Location(new LatLong(Integer.parseInt(array[0]), Integer.parseInt(array[1])));
                Integer reportNum = (int)result.getDouble("reportNum");
                WaterSourceReport wsReport
                        = new WaterSourceReport(reportNum, nameOfReporter, date, time, location, overallCondition, type);
                wsReports.add(wsReport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void restoreWqReports() {
        wqReports.removeAll();
        String query = "SELECT * FROM waterquality";

        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            while(result.next()) {
                String time = result.getString("time");
                String nameOfReporter = result.getString("name");
                LocalDate date = LocalDate.parse(result.getString("date"));
                String latlong = result.getString("location");
                latlong = latlong.replaceAll("[^0-9.]", "");
                String array[] = latlong.split("\\s+");
                Location location = new Location(new LatLong(Integer.parseInt(array[0]), Integer.parseInt(array[1])));
                Integer reportNum = (int)result.getDouble("reportNum");
                OverallCondition overallCondition = OverallCondition.valueOf(result.getString("overallCondition"));
                String virusPPM = Double.toString(result.getDouble("virusPPM"));
                String contaminantPPM = Double.toString(result.getDouble("contaminantPPM"));
                WaterQualityReport wqReport
                        = new WaterQualityReport(reportNum, nameOfReporter, date, time, location, overallCondition, virusPPM, contaminantPPM);
                wqReports.add(wqReport);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<User> getUsers() { return users; }
    public ObservableList<WaterSourceReport> getWsReports() { return wsReports; }
    public ObservableList<WaterQualityReport> getWqReports() { return wqReports; }
}
