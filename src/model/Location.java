package model;


import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.lynden.gmapsfx.javascript.object.LatLong;
import java.util.concurrent.TimeUnit;

/**
 * Created by twalker61 on 10/18/16.
 */
public class Location {

    private double longitude;
    private double latitude;
    private final String description;
    private final String name;
    private LatLong latLong;

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

    /**
     * initializes location using only latitude and longitude coordniates
     * @param name name of location
     * @param description name of description
     * @param latLong latLong of location
     */
    public Location(String name, String description, LatLong latLong) {
        this.name = name;
        this.description = description;
        longitude = latLong.getLongitude();
        latitude = latLong.getLatitude();
        this.latLong = latLong;
    }

    /**
     * sets new latitude and longitude coordinates
     * @param latLong new latLong of location
     */
    public void setLatLong(LatLong latLong) {
        this.latLong = latLong;
    }

    /**
     * Calls Google API to obtain longitude and latitude from address string name
     */
    private void setLatLong() {
        GeoApiContext context = new GeoApiContext();
        context = context.setApiKey("AIzaSyBGCSUhS73bdmKgWHaSRRMbICVYsOP3qn4")
                    .setConnectTimeout(60L, TimeUnit.SECONDS)
                    .setReadTimeout(60L, TimeUnit.SECONDS)
                    .setWriteTimeout(60L, TimeUnit.SECONDS);
        GeocodingApiRequest request = GeocodingApi.geocode(context, name);
        try {
            GeocodingResult result = request.await()[0];
            LatLng loc = result.geometry.location;
            latitude = loc.lat;
            longitude = loc.lng;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
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
