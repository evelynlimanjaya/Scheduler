package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ContactDAOImpl {

    public static ObservableList<Contact> getAllContacts() throws SQLException {
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String sqlStatement = "SELECT * from contacts";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        while (result.next()){
            int contactID = result.getInt("Contact_ID");
            String contactName = result.getString("Contact_Name");
            String email = result.getString("Email");
            Contact contactResult = new Contact(contactID, contactName, email);
            allContacts.add(contactResult);
        }

        return allContacts;
    }

    public static Contact getContactByID(int id) throws SQLException {
        Contact contact = null;
        DBConnection.getConnection();
        String sqlStatement = "SELECT * from contacts WHERE Contact_ID = " + id;
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        while (result.next()){
            int contactID = result.getInt("Contact_ID");
            String contactName = result.getString("Contact_Name");
            String email = result.getString("Email");
            contact = new Contact(contactID, contactName, email);
        }

        return contact;
    }

}
