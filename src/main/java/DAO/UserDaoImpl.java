package DAO;

import javafx.collections.ObservableList;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class implements Data Access Object design model for the users.
 */
public class UserDaoImpl {
    /**
     * This method gets a user that matches the username.
     * @param userName Username to be searched.
     * @return User that matches the username.
     * @throws SQLException
     * @throws Exception
     */
//    static boolean act;
    public static User getUser(String userName) throws SQLException, Exception {
        DBConnection.getConnection();
        String sqlStatement = "SELECT * FROM users WHERE User_Name ='" + userName + "'";
        Query.makeQuery(sqlStatement);
        User userResult = null;
        ResultSet result=Query.getResult();
        while (result.next()){
            int userID = result.getInt("User_ID");
            String password = result.getString("Password");
            String createDate = result.getString("Create_Date");
            String createdBy = result.getString("Created_By");
            String lastUpdate = result.getString("Last_Update");
            String lastUpdatedBy = result.getString("Last_Updated_by");
            userResult = new User(userID, userName, password, createDate, createdBy, lastUpdate, lastUpdatedBy);
        }
        return userResult;
    }

    /**
     * This method get the user ID that matches the username.
     * @param userName Username to be searched.
     * @return User ID that matches the username.
     * @throws SQLException
     */
    public static int getUserID(String userName) throws SQLException {
        DBConnection.getConnection();
        String sqlStatement = "SELECT * FROM users WHERE User_Name ='" + userName + "'";
        Query.makeQuery(sqlStatement);
        int userIDResult = 0;
        ResultSet result=Query.getResult();
        while (result.next()){
            userIDResult = result.getInt("User_ID");

        }
        return userIDResult;
    }
}
