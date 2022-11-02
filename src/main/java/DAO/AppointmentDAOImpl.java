package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * This class implements Data Access Object design model for the appointments.
 */
public class AppointmentDAOImpl {
    /**
     * This method gets all appointments from the database.
     * @return ObservableList of all the appointments.
     * @throws SQLException
     * @throws Exception
     */
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

    /**
     * This method gets all appointments from the current week.
     * @return ObservableList of all appointments from the current week.
     * @throws SQLException
     * @throws Exception
     */
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

    /**
     * This method gets all appointments from the current month.
     * @return ObservableList of all appointments from the current month.
     * @throws Exception
     */
    public static ObservableList<Appointment> getAllAppointmentsMonth() throws Exception{
        ObservableList<Appointment> allAppointmentsMonth = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String sqlStatement = "SELECT * from appointments WHERE YEAR(Start) = YEAR(NOW()) AND MONTH(Start) = MONTH(NOW());";
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

    /**
     * This method gets the largest appointment ID from the database.
     * @return Integer value of the largest appointment ID.
     * @throws Exception
     */
    public static int getLargestID() throws Exception{
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

    /**
     * This method gets all the appointments of a customer ID.
     * @param id Customer ID to be searched.
     * @return ObservableList of all the appointments of a customer ID.
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointmentsByCustID(int id) throws SQLException {
        ObservableList<Appointment> allAppointmentsByID = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String sqlStatement = "SELECT * from appointments WHERE Customer_ID = " + id;
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
            allAppointmentsByID.add(appointmentResult);
        }

        return allAppointmentsByID;
    }

    /**
     * This method gets all the appointments of a user ID.
     * @param id User ID to be searched.
     * @return ObservableList of all the appointments of a user ID.
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointmentsByUserID(int id) throws SQLException {
        ObservableList<Appointment> allAppointmentsByID = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String sqlStatement = "SELECT * from appointments WHERE User_ID = " + id;
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
            allAppointmentsByID.add(appointmentResult);
        }

        return allAppointmentsByID;
    }

    /**
     * This method gets all the appointments of a contact ID.
     * @param id Contact ID to be searched.
     * @return ObservableList of all the appointments of a contact ID.
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointmentsByContactID(int id) throws SQLException {
        ObservableList<Appointment> allAppointmentsByID = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String sqlStatement = "SELECT * from appointments WHERE Contact_ID = " + id;
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
            allAppointmentsByID.add(appointmentResult);
        }

        return allAppointmentsByID;
    }

    /**
     * This method gets all the appointments that match the selected month and type.
     * @param month Month to be searched.
     * @param typeInput Type to be searched.
     * @return ObservableList of all the appointments that match.
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointmentsByMonthType(int month, String typeInput) throws SQLException {
        ObservableList<Appointment> allAppointmentsByMonthType = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String sqlStatement = "SELECT * from appointments WHERE MONTH(Start) = " + month + " AND Type = '" + typeInput + "'";
        System.out.println(sqlStatement);
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
            allAppointmentsByMonthType.add(appointmentResult);
        }

        return allAppointmentsByMonthType;
    }

    /**
     * This method gets all the appointments of a location.
     * @param loc Location to be searched.
     * @return ObservableList of all the appointments of a location.
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointmentsByLoc(String loc) throws SQLException {
        ObservableList<Appointment> allAppointmentsByLoc = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String sqlStatement = "SELECT * from appointments WHERE Location = '" + loc + "'";
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
            allAppointmentsByLoc.add(appointmentResult);
        }

        return allAppointmentsByLoc;
    }

}
