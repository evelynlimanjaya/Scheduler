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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * This class controls the GUI elements of add_appointment.fxml.
 */
public class AddAppointmentController implements Initializable {
    public TextField title;
    public TextField description;
    public TextField location;
    public Button add_btn;
    public Button back_btn;
    public Label id_label;
    public ComboBox<Customer> customer_opt;
    public ComboBox<Contact> contact_opt;
    public ComboBox<LocalTime> start_time;
    public ComboBox<LocalTime> end_time;
    public DatePicker date;
    public ComboBox<String> type_opt;


    /**
     * This method is used to initialize the controller.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            id_label.setText(Integer.toString(AppointmentDAOImpl.getLargestID() + 1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        type_opt.getItems().add("Planning Session");
        type_opt.getItems().add("De-Briefing");
        type_opt.getItems().add("Kickstart");
        type_opt.getItems().add("Sales");
        type_opt.getItems().add("Design");

        LocalDate ESTDate = LocalDate.now(ZoneId.of("America/New_York"));
        LocalTime ESTStartTime = LocalTime.of(8,0);
        LocalTime ESTEndTime = LocalTime.of(22, 0);
        ZonedDateTime ESTStartZdt = ZonedDateTime.of(ESTDate, ESTStartTime, ZoneId.of("America/New_York"));
        ZonedDateTime ESTEndZdt = ZonedDateTime.of(ESTDate,ESTEndTime, ZoneId.of("America/New_York"));
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

        ZonedDateTime localStartZdt = ESTStartZdt.withZoneSameInstant(localZoneId);
        ZonedDateTime localEndZdt = ESTEndZdt.withZoneSameInstant(localZoneId);

        LocalTime localStartTime = localStartZdt.toLocalTime();
        LocalTime localEndTime = localEndZdt.toLocalTime();

        while (localStartTime.isBefore(localEndTime.minusHours(1))){
            start_time.getItems().add(localStartTime);
            localStartTime = localStartTime.plusMinutes(10);
        }

        localStartTime = localStartZdt.toLocalTime();

        while (localStartTime.isBefore(localEndTime.plusMinutes(10))){
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
    }

    /**
     * This method adds a new appointment to the database when 'Add' button is clicked.
     * @param actionEvent This is an Event representing some type of action.
     */
    public void on_add_btn(ActionEvent actionEvent) {
        try {
            int appID = Integer.parseInt(id_label.getText());
            String titleInput = title.getText();
            String descInput = description.getText();
            String locInput = location.getText();
            String typeInput = type_opt.getSelectionModel().getSelectedItem();
            LocalDate dateInput = date.getValue();
            LocalTime startTimeInput = start_time.getSelectionModel().getSelectedItem();
            LocalTime endTimeInput = end_time.getSelectionModel().getSelectedItem();
            LocalDateTime createDate = LocalDateTime.now();
            String createdBy = LoginController.userNameInput;
            LocalDateTime lastUpdate = LocalDateTime.now();
            String lastUpdatedBy = LoginController.userNameInput;
            Customer custIDInput = customer_opt.getSelectionModel().getSelectedItem();
            int custID = custIDInput.getCustomerID();
            int userIDInput = UserDaoImpl.getUserID(LoginController.userNameInput);
            Contact contactInput = contact_opt.getSelectionModel().getSelectedItem();
            int contID = contactInput.getContactID();

            ObservableList<Appointment> appointments = AppointmentDAOImpl.getAllAppointmentsByCustID(custID);
            ArrayList<Boolean> timeCheckResult = new ArrayList<>(appointments.size());

            for(Appointment a: appointments){
                LocalTime dbStartTime = a.getStartDateTime().toLocalTime();
                LocalTime dbEndTime = a.getEndDateTime().toLocalTime();

                if(a.getStartDateTime().toLocalDate().equals(dateInput) && checkOverlap(dbStartTime, dbEndTime, startTimeInput, endTimeInput)){
                    timeCheckResult.add(true);
                } else {
                    timeCheckResult.add(false);
                }
            }

            if(titleInput.isBlank() || descInput.isBlank() || locInput.isBlank()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setContentText("Please fill all fields.");
                alert.showAndWait();
            }

            if(timeCheckResult.contains(true)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Time Overlap");
                alert.setContentText("Appointment time overlaps with previously made appointment.");
                alert.showAndWait();
            } else if (LocalDateTime.of(dateInput, startTimeInput).isBefore(LocalDateTime.now().plusMinutes(30))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Time error");
                alert.setContentText("Please schedule an appointment that starts at least 30 minutes from now.");
                alert.showAndWait();
            } else if(endTimeInput.equals(startTimeInput) || endTimeInput.isBefore(startTimeInput)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Time error");
                alert.setContentText("Meeting end time has to be set after start time.");
                alert.showAndWait();
            } else {
                String sql = "INSERT INTO client_schedule.appointments  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

                ps.setInt(1, appID);
                ps.setString(2, titleInput);
                ps.setString(3, descInput);
                ps.setString(4, locInput);
                ps.setString(5, typeInput);
                ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.of(dateInput, startTimeInput)));
                ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.of(dateInput, endTimeInput)));
                ps.setTimestamp(8, Timestamp.valueOf(createDate));
                ps.setString(9, createdBy);
                ps.setTimestamp(10, Timestamp.valueOf(lastUpdate));
                ps.setString(11, lastUpdatedBy);
                ps.setInt(12, custID);
                ps.setInt(13, userIDInput);
                ps.setInt(14, contID);

                ps.execute();

                FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("appointment_record.fxml"));
                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
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
            ioe.printStackTrace();
        }
    }

    /**
     * This method compares two schedules and checks if they are overlapped or not.
     * @param aStart
     * @param aEnd
     * @param bStart
     * @param bEnd
     * @return
     */
    public static boolean checkOverlap(LocalTime aStart, LocalTime aEnd, LocalTime bStart, LocalTime bEnd){
        return ((bStart.isAfter(aStart) || bStart.equals(aStart)) && bStart.isBefore(aEnd)) ||
                (bEnd.isAfter(aStart) && (bEnd.isBefore(aEnd) || bEnd.equals(aEnd))) ||
                ((bStart.isBefore(aStart) || bStart.equals(aStart)) && (bEnd.isAfter(aEnd) || bEnd.equals(aEnd)));
    }

    /**
     * This method takes user to the appointment record when 'Go Back' button is clicked.
     * @param actionEvent This is an Event representing some type of action.
     * @throws IOException
     */
    public void on_back_btn(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You will lose all changes if you go back now. Do you want to proceed?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("appointment_record.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1800, 400);
            stage.setTitle("Appointment Record");
            stage.setScene(scene);
            stage.show();
        }
    }
}
