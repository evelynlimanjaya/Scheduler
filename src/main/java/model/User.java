package model;

public class User {
    private int userID;
    private String userName;
    private String password;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;

    public User(int userID, String userName, String password, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy){
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this. lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
}
