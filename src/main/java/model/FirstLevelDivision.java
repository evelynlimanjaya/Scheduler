package model;

import java.sql.Timestamp;

/**
 * This class represents the first-level division.
 */
public class FirstLevelDivision {
    /**
     * Division ID
     */
    private int divisionID;
    /**
     * Division name
     */
    private String division;
    /**
     * Create date and time
     */
    private Timestamp createDate;
    /**
     * Who or what created the record
     */
    private String createdBy;
    /**
     * Last update date and time
     */
    private Timestamp lastUpdate;
    /**
     * Who or what updated the record
     */
    private String lastUpdatedBy;
    /**
     * Country ID that relates to the division
     */
    private int countryID;

    /**
     * This is the constructor method of the first-level division object.
     * @param divisionID Division ID
     * @param division Division name
     * @param createDate Create date and time
     * @param createdBy Who or what created the record
     * @param lastUpdate Last update date and time
     * @param lastUpdatedBy Who or what updated the record
     * @param countryID Country ID that relates to the division
     */
    public FirstLevelDivision(int divisionID, String division, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int countryID){

        this.divisionID = divisionID;
        this.division = division;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
    }

    /**
     * This method gets the division ID.
     * @return Division ID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * This method gets the division name.
     * @return Division name
     */
    public String getDivision() {
        return division;
    }

    /**
     * This method gets the create date.
     * @return Create date
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * This method gets who or what created the record.
     * @return Who or what created the record
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method gets last update date and time.
     * @return Last update date and time
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * This method gets who or what updated the record.
     * @return Who or what updated the record
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * This method gets the country ID that relates to the division.
     * @return Country ID that relates to the division
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * This method overrides the toString method to return division name when this method is called.
     * @return Division name
     */
    @Override
    public String toString(){
        return division;
    }
}
