package model;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Taiga on 10/12/2016.
 */
public class WaterReport {
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty location = new SimpleStringProperty();
    private  Location locationObject;
    private final ObjectProperty<WaterCondition> condition = new SimpleObjectProperty<>();
    private final ObjectProperty<WaterType> type = new SimpleObjectProperty<>();

    /**
     * initializes report time, water condition and type, and location object
     * @param condition water's condition
     * @param type water's type
     */
    public WaterReport(String time, String location, WaterCondition condition, WaterType type) {
        this.time.set(time);
        this.location.set(location);
        this.condition.set(condition);
        this.type.set(type);
        locationObject = new Location(
                "Water Type: " + type
                + "Water Condition " + condition, location);
    }

    /**
     * returns time of report
     * @return time of report
     */
    public String getTime() {return time.get();}

    /**
     * returns location of report
     * @return location of report
     */
    public String getLocation() {return location.get();}

    /**
     * returns location object of report
     * @return location object of report
     */
    public Location getLocationObject() {return locationObject;}

    /**
     * returns water condition of report
     * @return water condition of report
     */
    public WaterCondition getCondition() {return condition.get();}

    /**
     * returns water type of report
     * @return water type of report
     */
    public WaterType getType() {return type.get();}

    /**
     * change time of report
     * @param time
     */
    public void setTime(String time) {this.time.set(time);}

    /**
     * change location of report
     * @param location
     */
    public void setLocation(String location) {this.location.set(location);}

    /**
     * change water condition in report
     * @param condition of water
     */
    public void setCondition(WaterCondition condition) {this.condition.set(condition);}

    /**
     * change water type in report
     * @param type of water
     */
    public void setType(WaterType type) {this.type.set(type);}

    /**
     * returns the string concatenation of the water report data
     * @return
     */
    @Override
    public String toString() {
        return time.get() + " / " + location.get() + " / " + condition.get().toString() + " / " + type.get().toString();
    }
}
