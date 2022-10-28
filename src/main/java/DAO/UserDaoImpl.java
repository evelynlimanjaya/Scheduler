package DAO;

import javafx.collections.ObservableList;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl {
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
