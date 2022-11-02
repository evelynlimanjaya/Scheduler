package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This class represents the appointment.
 */
public class Appointment {
    /**
     * This is the appointment ID.
     */
    private int appointmentID;
    /**
     * This is the appointment title.
     */
    private String title;
    /**
     * This is the appointment description.
     */
    private String description;
    /**
     * This is the appointment location.
     */
    private String location;
    /**
     * This is the appointment type.
     */
    private String type;
    /**
     * This is the start date and time of the appointment.
     */
    private LocalDateTime startDateTime;
    /**
     * This is the end date and time of the appointment.
     */
    private LocalDateTime endDateTime;
    /**
     * This is the date when the appointment is created.
     */
    private LocalDateTime createDate;
    /**
     * This is the information of who or what created the appointment.
     */
    private String createdBy;
    /**
     * This is the date when the appointment is last updated.
     */
    private LocalDateTime lastUpdate;
    /**
     * This is the information of who or what updated the appointment.
     */
    private String lastUpdatedBy;
    /**
     * This is the customer ID that relates to the appointment.
     */
    private int customerID;
    /**
     * This is the user ID that relates to the appointment.
     */
    private int userID;
    /**
     * This is the contact ID that relates to the appointment.
     */
    private int contactID;

    /**
     * This is the constructor method of the appointment object.
     * @param appointmentID Appointment ID
     * @param title Appointment title
     * @param description Appointment description
     * @param location Appointment location
     * @param type Appointment type
     * @param startDateTime Appointment start date and time
     * @param endDateTime Appointment end date and time
     * @param createDate Appointment create date and time
     * @param createdBy Who or what created the appointment
     * @param lastUpdate Appointment last update date and time
     * @param lastUpdatedBy Who or what updated the appointment
     * @param customerID Customer ID that relates to the appointment
     * @param userID User ID that relates to the appointment
     * @param contactID Contact ID that relates to the appointment
     */
    public Appointment(int appointmentID, String title, String description, String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID){

        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }


    /**
     * This method gets the appointment ID.
     * @return Appointment ID
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * This method gets the appointment title.
     * @return Appointment title
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method gets the appointment description.
     * @return Appointment description
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method gets the appointment location.
     * @return Appointment location
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method gets the appointment type.
     * @return Appointment type
     */
    public String getType() {
        return type;
    }

    /**
     * This method gets the appointment start date and time.
     * @return Appointment star date and time
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * This method gets the appointment end date and time.
     * @return Appointment end date and time
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * This method gets the appointment create date and time.
     * @return Appointment create date and time
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * This method gets who or what created the appointment.
     * @return Who or what created the appointment.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method gets the last update date and time.
     * @return Appointment last update date and time
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * This method gets who or what updated the appointment.
     * @return Who or what updated the appointment
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * This method gets the customer ID that relates to the appointment.
     * @return Customer ID that relates to the appointment
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * This method gets the User ID that relates to the appointment.
     * @return User ID that relates to the appointment
     */
    public int getUserID() {
        return userID;
    }

    /**
     * This method gets the contact ID that relates to the appointment.
     * @return Contact ID that relates to the appointment
     */
    public int getContactID() {
        return contactID;
    }
}
