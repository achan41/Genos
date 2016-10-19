package model;

/**
 * Created by twalker61 on 10/18/16.
 */
public class Location {

    private double longitude;
    private double latitude;
    private final String description;
    private final String name;

    /**
     * initialize location, set description and name, set latitude/longitude
     * @param desc water type and water condition of location
     * @param n name of location
     */
    public Location(String desc, String n) {
        description = desc;
        name = n;
        setLatLong();
    }

    private void setLatLong() {

    }

    /**
     * return description of location
     * @return description The water type and condition of location
     */
    public String getDescription() {
        return description;
    }

    /**
     * return name of location
     * @return name The string name of the location
     */
    public String getName() {
        return name;
    }

    /**
     * return latitude of location
     * @return latitude The latitude of location
     */
    public double getLat() {
        return latitude;
    }

    /**
     * return longitude of location
     * @return latitude The latitude of location
     */
    public double getLong() {
        return longitude;
    }
}
