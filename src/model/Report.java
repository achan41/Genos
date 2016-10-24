package model;

import java.time.LocalDate;

/**
 * Created by twalker61 on 10/24/16.
 * Unsure if we'll want an abstract or superclass, but it's here in the mean time
 */
public abstract class Report {

    public abstract String getLocation();

    public abstract String getTime();

    public abstract LocalDate getDate();

    public abstract int getReportNum();

    public abstract Location getLocationObject();

    public abstract void setLocation(String l);

    public abstract void setTime(String t);

    public abstract void setDate(LocalDate d);

    public abstract void setReportNum(int n);

    public abstract void setLocationObject(Location l);

}
