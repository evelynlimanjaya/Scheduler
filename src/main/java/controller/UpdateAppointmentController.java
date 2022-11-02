package controller;

import DAO.*;
import com.elimanjaya.scheduler.SchedulerMain;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * This class controls the GUI elements of update_appointment.fxml.
 */
public class UpdateAppointmentController implements Initializable {
    private static Appointment retrievedAppt = null;
    public TextField title;
    public TextField description;
    public TextField location;
    public TextField type;
    public Button update_btn;
    public Button back_btn;
    public Label id_label;
    public ComboBox customer_opt;
    public ComboBox contact_opt;
    public DatePicker date;
    public ComboBox start_time;
    public ComboBox end_time;
    public ComboBox type_opt;

    /**
     * This method is used to initialize the controller.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_label.setText(Integer.toString(retrievedAppt.getAppointmentID()));
        title.setText(retrievedAppt.getTitle());
        description.setText(retrievedAppt.getDescription());
        location.setText(retrievedAppt.getLocation());

        type_opt.getItems().add("Planning Session");
        type_opt.getItems().add("De-Briefing");
        type_opt.getItems().add("Kickstart");
        type_opt.getItems().add("Sales");
        type_opt.getItems().add("Design");

        LocalDate ESTDate = LocalDate.now(ZoneId.of("America/New_York"));
        LocalTime ESTStartTime = LocalTime.of(8, 00);
        LocalTime ESTEndTime = LocalTime.of(22, 00);
        ZonedDateTime ESTStartZdt = ZonedDateTime.of(ESTDate, ESTStartTime, ZoneId.of("America/New_York"));
        ZonedDateTime ESTEndZdt = ZonedDateTime.of(ESTDate, ESTEndTime, ZoneId.of("America/New_York"));
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

        ZonedDateTime localStartZdt = ESTStartZdt.withZoneSameInstant(localZoneId);
        ZonedDateTime localEndZdt = ESTEndZdt.withZoneSameInstant(localZoneId);

        LocalTime localStartTime = localStartZdt.toLocalTime();
        LocalTime localEndTime = localEndZdt.toLocalTime();

        while (localStartTime.isBefore(localEndTime.minusHours(1))) {
            start_time.getItems().add(localStartTime);
            localStartTime = localStartTime.plusMinutes(10);
        }

        localStartTime = localStartZdt.toLocalTime();

        while (localStartTime.isBefore(localEndTime.plusMinutes(10))) {
            end_time.getItems().add(localStartTime);
            localStartTime = localStartTime.plusMinutes(10);
        }

        try {
            ObservableList<Customer> customers = CustomerDAOImpl.getAllCustomers();
            customer_opt.setItems(customers);

            ObservableList<Contact> contacts = ContactDAOImpl.getAllContacts();
            contact_opt.setItems(contacts);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        type_opt.setValue(retrievedAppt.getType());
        date.setValue(retrievedAppt.getStartDateTime().toLocalDate());
        start_time.setValue(retrievedAppt.getStartDateTime().toLocalTime());
        end_time.setValue(retrievedAppt.getEndDateTime().toLocalTime());

        try {
            customer_opt.setValue(CustomerDAOImpl.getCustomerByID(retrievedAppt.getCustomerID()));
            contact_opt.setValue(ContactDAOImpl.getContactByID(retrievedAppt.getContactID()));
        } catch (SQLException e){
            System.out.println(e);
        }

    }

    /**
     * This method retrieves the appointment passed from another class used to get the selected appointment from a table view.
     * @param a Appointment object that is passed from another class.
     */
    public static void passData(Appointment a) {
        retrievedAppt = a;
    }

    /**
     * This method updates the appointment data in the database when 'Update' button is clicked.
     * @param actionEvent This is an Event representing some type of action.
     */
    public void on_update_btn(ActionEvent actionEvent) {
        try {
            int appID = Integer.parseInt(id_label.getText());
            String titleInput = title.getText();
            String descInput = description.getText();
            String locInput = location.getText();
            String typeInput = type_opt.getSelectionModel().getSelectedItem().toString();
            LocalDate dateInput = date.getValue();
            LocalTime startTimeInput = (LocalTime) start_time.getSelectionModel().getSelectedItem();
            LocalTime endTimeInput = (LocalTime) end_time.getSelectionModel().getSelectedItem();
            LocalDateTime lastUpdate = LocalDateTime.now();
            String lastUpdatedBy = LoginController.userNameInput;
            Customer custIDInput = (Customer) customer_opt.getSelectionModel().getSelectedItem();
            int custID = custIDInput.getCustomerID();
            int userIDInput = UserDaoImpl.getUserID(LoginController.userNameInput);
            Contact contactInput = (Contact) contact_opt.getSelectionModel().getSelectedItem();
            int contID = contactInput.getContactID();

            ObservableList<Appointment> appointments = AppointmentDAOImpl.getAllAppointmentsByCustID(custID);
            ArrayList timeCheckResult = new ArrayList(appointments.size());

            for (Appointment a : appointments) {
                LocalTime dbStartTime = a.getStartDateTime().toLocalTime();
                LocalTime dbEndTime = a.getEndDateTime().toLocalTime();

                if(a.getAppointmentID() == appID){
                    timeCheckResult.add(false);
                } else if (a.getStartDateTime().toLocalDate().equals(dateInput) && AddAppointmentController.checkOverlap(dbStartTime, dbEndTime, startTimeInput, endTimeInput)) {
                    timeCheckResult.add(true);
                } else {
                    timeCheckResult.add(false);
                }
            }

            if (timeCheckResult.contains(true)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Time Overlap");
                alert.setContentText("Appointment time overlaps with previously made appointment.");
                alert.showAndWait();
            } else if (LocalDateTime.of(dateInput, startTimeInput).isBefore(LocalDateTime.now().plusMinutes(30))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Time error");
                alert.setContentText("Please schedule an appointment that starts at least 30 minutes from now.");
                alert.showAndWait();
            } else if (endTimeInput.equals(startTimeInput) || endTimeInput.isBefore(startTimeInput)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Time error");
                alert.setContentText("Meeting end time has to be set after start time.");
                alert.showAndWait();
            } else {
                String sql = "UPDATE client_schedule.appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";

                PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

                ps.setString(1, titleInput);
                ps.setString(2, descInput);
                ps.setString(3, locInput);
                ps.setString(4, typeInput);
                ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.of(dateInput, startTimeInput)));
                ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.of(dateInput, endTimeInput)));
                ps.setTimestamp(7, Timestamp.valueOf(lastUpdate));
                ps.setString(8, lastUpdatedBy);
                ps.setInt(9, custID);
                ps.setInt(10, userIDInput);
                ps.setInt(11, contID);
                ps.setInt(12, appID);

                ps.execute();

                FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("appointment_record.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 1800, 400);
                stage.setTitle("Appointment Record");
                stage.setScene(scene);
                stage.show();
            }
        } catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Please input the correct values:\n - Customer Name : maximum 50 characters\n - Address : maximum 100 characters\n - Postal Code : maximum 50 characters\n - Phone : maximum 50 characters" );
            alert.showAndWait();
        } catch (NullPointerException npe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Please input the correct values:\n - Date, start time, end time, customer, and contact have to be selected" );
            alert.showAndWait();
        } catch (IOException ioe){
            System.out.println(ioe);
        }
    }

    /**
     * This method takes the user to appointment record when 'Go Back' button is clicked.
     * @param actionEvent This is an Event representing some type of action.
     * @throws IOException
     */
    public void on_back_btn(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You will lose all changes if you go back now. Do you want to proceed?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("appointment_record.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1800, 400);
            stage.setTitle("Appointment Record");
            stage.setScene(scene);
            stage.show();
        }
    }
}