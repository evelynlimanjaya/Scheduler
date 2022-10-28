package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CustomerDAOImpl {
    public static ObservableList<Customer> getAllCustomers() throws SQLException, Exception{
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String sqlStatement = "SELECT * from customers";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        while (result.next()){
            int customerID = result.getInt("Customer_ID");
            String customerName = result.getString("Customer_Name");
            String address = result.getString("Address");
            String postalCode = result.getString("Postal_Code");
            String phone = result.getString("Phone");
            Timestamp createDate = result.getTimestamp("Create_Date");
            String createdBy = result.getString("Created_By");
            Timestamp lastUpdate = result.getTimestamp("Last_Update");
            String lastUpdatedBy = result.getString("Last_Updated_By");
            int divisionID = result.getInt("Division_ID");
            Customer customerResult = new Customer(customerID, customerName, address, postalCode, phone, createDate.toLocalDateTime(), createdBy, lastUpdate.toLocalDateTime(), lastUpdatedBy, divisionID);
            allCustomers.add(customerResult);
        }

        return allCustomers;
    }

    public static int getLargestID() throws SQLException, Exception{
        int largestID = 0;
        DBConnection.getConnection();
        String sqlStatement = "SELECT MAX(Customer_ID) FROM customers";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        while (result.next()){
            largestID = result.getInt("MAX(Customer_ID)");
        }
        return largestID;

    }
}
