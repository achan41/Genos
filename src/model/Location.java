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
    private String description;
    private String name;
    private LatLong latLong;

    /**
     * initialize location, set description and name, set latitude/longitude
     * @param desc water type and water condition of location
     * @param n name of location
     */
    public Location(String n, String desc, boolean setLatLong) {
        description = desc;
        name = n;
        if (setLatLong) {
            setLatLong(name);
        }
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
    private void setLatLong(String name) {
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

    public String getLatLongString() {
        String locText = "";
        if (latLong.getLatitude() > 0) {
            locText += Math.floor(latLong.getLatitude()*100)/100 + "*N ";
        } else {
            locText += Math.floor(latLong.getLatitude()*100)/100 + "*S ";
        }
        if (latLong.getLongitude() > 0) {
            locText += Math.floor(latLong.getLongitude()*100)/100 + "*E";
        } else {
            locText += Math.floor(latLong.getLongitude()*100)/100 + "*W";
        }
        return locText;
    }

    /**
     * changes name of description
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * changes description of location
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
