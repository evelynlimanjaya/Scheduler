package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CountriesDAOImpl {
    public static ObservableList<Country> getAllCountries() throws SQLException, Exception{
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String sqlStatement = "SELECT * from countries";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        while (result.next()){
            int countryID = result.getInt("Country_ID");
            String country = result.getString("Country");
            Timestamp createDate = result.getTimestamp("Create_Date");
            String createdBy = result.getString("Created_By");
            Timestamp lastUpdate = result.getTimestamp("Last_Update");
            String lastUpdatedBy = result.getString("Last_Updated_By");
            Country countryResult = new Country(countryID, country, createDate, createdBy, lastUpdate, lastUpdatedBy);
            allCountries.add(countryResult);
        }

        return allCountries;
    }


}
