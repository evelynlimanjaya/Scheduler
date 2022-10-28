package model;

import java.sql.Timestamp;

public class Country {
    private int countryID;
    private String country;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    public Country(int countryID, String country, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy){

        this.countryID = countryID;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getCountryID() {
        return countryID;
    }

    public String getCountry() {
        return country;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Override
    public String toString(){
        return (country);
    }
}
