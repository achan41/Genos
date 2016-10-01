package model;


/**
 * Created by Taiga on 10/1/2016.
 */
public enum AccountType {
    USR ("User", "USR"),
    WKR ("Worker", "WKR"),
    MGR ("Manager", "MGR"),
    ADM ("Admin","ADM");

    private final String name;
    private final String charRep;

    /**
     * Counstructor for AccountType enumeration
     * @param cName
     * @param cRep
     */
    AccountType(String cName, String cRep) {
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
