package model;

import java.time.LocalDateTime;


/**
 * This class represents the customer.
 */
public class Customer {
    /**
     * Customer ID
     */
    private int customerID;
    /**
     * Customer name
     */
    private String customerName;
    /**
     * Customer address
     */
    private String address;
    /**
     * Customer postal code
     */
    private String postalCode;
    /**
     * Customer phone number
     */
    private String phone;
    /**
     * Create date and time
     */
    private LocalDateTime createDate;
    /**
     * Who or what created the record
     */
    private String createdBy;
    /**
     * Last update date and time
     */
    private LocalDateTime lastUpdate;
    /**
     * Who or what updated the record
     */
    private String lastUpdatedBy;
    /**
     * Division ID that relates to the customer
     */
    private int divisionID;

    /**
     * This is the constructor method of the customer object.
     * @param customerID Customer ID
     * @param customerName Customer name
     * @param address Customer address
     * @param postalCode Customer postal code
     * @param phone Customer phone number
     * @param createDate Create date and time
     * @param createdBy Who or what created the record
     * @param lastUpdate Last update date and time
     * @param lastUpdatedBy Who or what created the record
     * @param divisionID Division ID that relates to the customer
     */
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

    /**
     * This method gets the customer name.
     * @return Customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * This method gets the customer ID.
     * @return Customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * This method gets the customer address.
     * @return Customer address
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method gets the customer postal code.
     * @return Customer postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * This method gets the customer phone number.
     * @return Customer phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method gets the created date and time.
     * @return Create date and time
     */
    public LocalDateTime getCreateDate() {
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
    public LocalDateTime getLastUpdate() {
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
     * This method gets the division ID that relates to the customer.
     * @return Division ID that relates to the customer.
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * This method overrides the toString method to return customer name when this method is called.
     * @return Customer name
     */
    @Override
    public String toString(){
        return (customerName);
    }
}
