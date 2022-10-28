package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Customer {
    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;

    public Customer(int customerID, String customerName, String address, String postalCode, String phone, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int divisionID){

        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public int getDivisionID() {
        return divisionID;
    }

    @Override
    public String toString(){
        return (customerName);
    }
}
