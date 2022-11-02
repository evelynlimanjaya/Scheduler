package model;

/**
 * This class represents the user.
 */
public class User {
    /**
     * User ID
     */
    private int userID;
    /**
     * User name
     */
    private String userName;
    /**
     * Password
     */
    private String password;
    /**
     * Create date and time
     */
    private String createDate;
    /**
     * Who or what created the user
     */
    private String createdBy;
    /**
     * Last update date and time
     */
    private String lastUpdate;
    /**
     * Who or what updated the user
     */
    private String lastUpdatedBy;

    /**
     * This is the constructor method of the user object.
     * @param userID User ID
     * @param userName User name
     * @param password Password
     * @param createDate Create date and time
     * @param createdBy Who or what creaated the user
     * @param lastUpdate Last update date and time
     * @param lastUpdatedBy Who or what updated the user
     */
    public User(int userID, String userName, String password, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy){
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this. lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * This method gets the user ID.
     * @return User ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * This method gets the username.
     * @return Username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method gets the password.
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method gets the created date and time.
     * @return Create date and time
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * This method gets who or what created the user.
     * @return Who or what created the user
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method gets last update date and time.
     * @return Last update date and time
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * This method gets who or what updated the user.
     * @return Who or what updated the user
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
}
