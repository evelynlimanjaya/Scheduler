package model;

import java.sql.Timestamp;

/**
 * This class represents the country.
 */
public class Country {
    /**
     * Country ID
     */
    private int countryID;
    /**
     * Country name
     */
    private String country;
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
     * This is the constructor method of the country object.
     * @param countryID Country ID
     * @param country Country name
     * @param createDate Create date and time
     * @param createdBy Who or what created the record
     * @param lastUpdate Last update date and time
     * @param lastUpdatedBy Who or what updated the record
     */
    public Country(int countryID, String country, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy){

        this.countryID = countryID;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * This method gets the country ID.
     * @return Country ID
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * This method gets the country name.
     * @return Country name
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method gets the created date and time.
     * @return Create date and time
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
     * This method overrides the toString method to return country name when this method is called.
     * @return Country name
     */
    @Override
    public String toString(){
        return (country);
    }
}
