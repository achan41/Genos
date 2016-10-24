package model;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * Created by twalker61 on 10/24/16.
 */
public class WaterPurityReport {

    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private Location location;
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    private final IntegerProperty reportNum = new SimpleIntegerProperty();
    private final ObjectProperty<OverallCondition> overallCondition = new SimpleObjectProperty<>();
    private String virusPPM;
    private String contaminantPPM;

    /**
     * initializes report time, water condition and type, and location object
     * @param
     */


    public WaterPurityReport(int reportNum, String reporterName, LocalDate date, String time, Location location,
                             OverallCondition overallCondition, String virusPPM, String contamPPM) {
        this.reportNum.set(reportNum);
        this.name.set(reporterName);
        this.date.set(date);
        this.time.set(time);
        this.location = location;
        this.overallCondition.set(overallCondition);
        this.virusPPM = virusPPM;
        this.contaminantPPM = contamPPM;
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
    public int getReportNum() {return reportNum.getValue();}

    /**
     * returns name of reporter
     * @return name of reporter of water report
     */
    public String getReporterName() {return name.get();}

    /**
     * returns time of report
     * @return time of report
     */
    public LocalDate getDate() {return date.get();}

    /**
     * returns location of report
     * @return location of report
     */
    public Location getLocation() {return location;}

    /**
     * returns location object of report
     * @return location object of report
     */
    public Location getLocationObject() {return location;}

    /**
     * returns overall condition
     * @return overall condition of report
     */
    public OverallCondition getOverallCondition() {
        return overallCondition.get();
    }

    /**
     * returns water type of report
     * @return water type of report
     */

    public double getVirusPPM() {
        return Double.parseDouble(virusPPM);
    }

    public double getContamPPM() {
        return Double.parseDouble(contaminantPPM);
    }

    /**
     * change time of report
     * @param time new time of report
     */
    public void setTime(String time) {this.time.set(time);}

    /**
     * change location of report
     * @param location new location of report
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    public void setOverallCondition(OverallCondition condition) {this.overallCondition.set(condition);}

    public void setVirusPPM(String ppm) {virusPPM = ppm;}

    public void setContaminantPPM(String ppm) {contaminantPPM = ppm;}

    /**
     * change water type in report
     * @param num new report number
     */
    public void setReportNum(int num) {this.reportNum.setValue(num);}

    /**
     * returns the string concatenation of the water report data
     * @return
     */
    @Override
    public String toString() {
        return reportNum.get() + " / "
                + date.get() + " / "
                + time.get() + " / "
                + location.getLatLongString() + "* / "
                + overallCondition.get().toString() + " / "
                + virusPPM + "/"
                + contaminantPPM;
    }
}
