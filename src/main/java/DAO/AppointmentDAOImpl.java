package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentDAOImpl {
    public static ObservableList<Appointment> getAllAppointments() throws SQLException, Exception{
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String sqlStatement = "SELECT * from appointments";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        while (result.next()){
            int appointmentID = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            String type = result.getString("Type");
            LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
            LocalDateTime createDate = result.getTimestamp("Create_Date").toLocalDateTime();
            String createdBy = result.getString("Created_By");
            LocalDateTime lastUpdate = result.getTimestamp("Last_Update").toLocalDateTime();
            String lastUpdatedBy = result.getString("Last_Updated_By");
            int customerID = result.getInt("Customer_ID");
            int userID = result.getInt("User_ID");
            int contactID = result.getInt("Contact_ID");
            Appointment appointmentResult = new Appointment(appointmentID, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
            allAppointments.add(appointmentResult);
        }

        return allAppointments;
    }

    public static ObservableList<Appointment> getAllAppointmentsWeek() throws SQLException, Exception{
        ObservableList<Appointment> allAppointmentsWeek = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String sqlStatement = "SELECT * from appointments WHERE YEAR(START) = YEAR(NOW()) AND WEEK(Start) = WEEK(NOW());";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        while (result.next()){
            int appointmentID = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            String type = result.getString("Type");
            LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
            LocalDateTime createDate = result.getTimestamp("Create_Date").toLocalDateTime();
            String createdBy = result.getString("Created_By");
            LocalDateTime lastUpdate = result.getTimestamp("Last_Update").toLocalDateTime();
            String lastUpdatedBy = result.getString("Last_Updated_By");
            int customerID = result.getInt("Customer_ID");
            int userID = result.getInt("User_ID");
            int contactID = result.getInt("Contact_ID");
            Appointment appointmentResult = new Appointment(appointmentID, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
            allAppointmentsWeek.add(appointmentResult);
        }

        return allAppointmentsWeek;
    }

    public static ObservableList<Appointment> getAllAppointmentsMonth() throws SQLException, Exception{
        ObservableList<Appointment> allAppointmentsMonth = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String sqlStatement = "SELECT * from appointments WHERE YEAR(START) = YEAR(NOW()) AND MONTH(Start) = MONTH(NOW());";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        while (result.next()){
            int appointmentID = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            String type = result.getString("Type");
            LocalDateTime start = result.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = result.getTimestamp("End").toLocalDateTime();
            LocalDateTime createDate = result.getTimestamp("Create_Date").toLocalDateTime();
            String createdBy = result.getString("Created_By");
            LocalDateTime lastUpdate = result.getTimestamp("Last_Update").toLocalDateTime();
            String lastUpdatedBy = result.getString("Last_Updated_By");
            int customerID = result.getInt("Customer_ID");
            int userID = result.getInt("User_ID");
            int contactID = result.getInt("Contact_ID");
            Appointment appointmentResult = new Appointment(appointmentID, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
            allAppointmentsMonth.add(appointmentResult);
        }

        return allAppointmentsMonth;
    }

    public static int getLargestID() throws SQLException, Exception{
        int largestID = 0;
        DBConnection.getConnection();
        String sqlStatement = "SELECT MAX(Appointment_ID) FROM appointments";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        while (result.next()){
            largestID = result.getInt("MAX(Appointment_ID)");
        }
        return largestID;

    }
}
