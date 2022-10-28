package controller;

import DAO.AppointmentDAOImpl;
import DAO.ContactDAOImpl;
import DAO.CustomerDAOImpl;
import DAO.UserDaoImpl;
import com.elimanjaya.scheduler.SchedulerMain;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Contact;
import model.Customer;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class AddAppointmentController implements Initializable {
    public TextField title;
    public TextField description;
    public TextField location;
    public TextField type;
    public Button add_btn;
    public Button back_btn;
    public Label id_label;
    public ComboBox customer_opt;
    public ComboBox contact_opt;
    public ComboBox start_time;
    public ComboBox end_time;
    public DatePicker date;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            id_label.setText(Integer.toString(AppointmentDAOImpl.getLargestID() + 1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        LocalDate ESTDate = LocalDate.now(ZoneId.of("America/New_York"));
        LocalTime ESTStartTime = LocalTime.of(8,00);
        LocalTime ESTEndTime = LocalTime.of(22,00);
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

    public void on_add_btn(ActionEvent actionEvent) throws SQLException {
        int appID = Integer.parseInt(id_label.getText());
        String titleInput = title.getText();
        String descInput = description.getText();
        String locInput = location.getText();
        String typeInput = type.getText();
        LocalDate dateInput = date.getValue();
        LocalTime startTimeInput = (LocalTime) start_time.getSelectionModel().getSelectedItem();
        LocalTime endTimeInput = (LocalTime) end_time.getSelectionModel().getSelectedItem();
        LocalDateTime createDate = LocalDateTime.now();
        String createdBy = LoginController.userNameInput;
        LocalDateTime lastUpdate = LocalDateTime.now();
        String lastUpdatedBy = LoginController.userNameInput;
        Customer custIDInput = (Customer) customer_opt.getSelectionModel().getSelectedItem();
        int custID = custIDInput.getCustomerID();
        int userIDInput = UserDaoImpl.getUserID(LoginController.userNameInput);
        Contact contactInput = (Contact) contact_opt.getSelectionModel().getSelectedItem();
        int contID = contactInput.getContactID();

    }

    public void on_back_btn(ActionEvent actionEvent) throws IOException {
        Alert alertDelete = new Alert(Alert.AlertType.CONFIRMATION, "You will lose all changes if you go back now. Do you want to proceed?");
        Optional<ButtonType> result = alertDelete.showAndWait();

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
