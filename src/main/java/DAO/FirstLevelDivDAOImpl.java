package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivision;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class FirstLevelDivDAOImpl {
    public static ObservableList<FirstLevelDivision> getAllDivisionsByCountryID(int id) throws SQLException, Exception{
        ObservableList<FirstLevelDivision> allDivisions = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String sqlStatement = "SELECT * from first_level_divisions WHERE Country_ID = " + id;
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        while (result.next()){
            int divisionID = result.getInt("Division_ID");
            String division = result.getString("Division");
            Timestamp createDate = result.getTimestamp("Create_Date");
            String createdBy = result.getString("Created_By");
            Timestamp lastUpdate = result.getTimestamp("Last_Update");
            String lastUpdatedBy = result.getString("Last_Updated_By");
            int countryID = result.getInt("Country_ID");
            FirstLevelDivision divisionResult = new FirstLevelDivision(divisionID, division, createDate, createdBy, lastUpdate, lastUpdatedBy, countryID);
            allDivisions.add(divisionResult);
        }
        return allDivisions;
    }
}
