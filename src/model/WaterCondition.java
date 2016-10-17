package model;

/**
 * Created by dionisiatara on 10/11/16.
 */
public enum WaterCondition {
    Waste ("Waste", "WST"),
    TreatableClear ("Treatable-Clear", "TCL"),
    TreatatableMuddy ("Treatable-Muddy", "TMD"),
    Potable ("Potable", "PTB");

    private final String name;
    private final String charRep;

    /**
     * Counstructor for WaterCondition enumeration
     * @param cName
     * @param cRep
     */
    WaterCondition(String cName, String cRep) {
        name = cName;
        charRep = cRep;
    }

    /**
     * returns the full string name
     * @return full name string
     */
    public String getName() {return name;}

    /**
     * returns the shortened char representation
     * @return char rep string
     */
    public String getCharRep() {return charRep;}

    /**
     * returns the shortened char rep
     * @return char rep string
     */
    public String toString() {return name;}
}
