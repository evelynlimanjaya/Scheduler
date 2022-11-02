package controller;

import DAO.AppointmentDAOImpl;
import DAO.ContactDAOImpl;
import com.elimanjaya.scheduler.SchedulerMain;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.util.ResourceBundle;

/**
 * This class controls the GUI elements of appt_summary_report.fxml.
 */
public class ApptSummaryReportController implements Initializable {
    public Label month_type_lbl;
    public ComboBox month_opt;
    public ComboBox type_opt;
    public ComboBox contact_opt;
    public ComboBox location_opt;
    public Label location_lbl;
    public Button back_btn;
    public TableView schedule_tbl;
    public TableColumn app_ID;
    public TableColumn title;
    public TableColumn type;
    public TableColumn desc;
    public TableColumn start;
    public TableColumn end;
    public TableColumn cust_ID;
    public Button display_btn;

    ObservableList<Appointment> appointments = AppointmentDAOImpl.getAllAppointments();

    /**
     * This method is used to initialize the controller.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Appointment a: appointments){
            if(!month_opt.getItems().contains(a.getStartDateTime().toLocalDate().getMonth())){
                month_opt.getItems().add(a.getStartDateTime().toLocalDate().getMonth());
            }

            if(!type_opt.getItems().contains(a.getType())){
                type_opt.getItems().add(a.getType());
            }

            Month now = a.getStartDateTime().toLocalDate().getMonth();
            int nowInt = now.getValue();
            System.out.println(nowInt);
        }

        ObservableList<Contact> contacts = null;
        try {
            contacts = ContactDAOImpl.getAllContacts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        contact_opt.setItems(contacts);


        for(Appointment a: appointments){
            if(!location_opt.getItems().contains(a.getLocation())){
                location_opt.getItems().add(a.getLocation());
            }

        }
    }

    public ApptSummaryReportController() throws Exception {
    }

    /**
     * This method populates the table view with the selected contact's appointments.
     * @param actionEvent This is an Event representing some type of action.
     * @throws SQLException
     */
    public void on_contact_opt(ActionEvent actionEvent) throws SQLException {
        Contact selectedCont = (Contact) contact_opt.getSelectionModel().getSelectedItem();
        int selectedID = selectedCont.getContactID();

        ObservableList<Appointment> contAppts = AppointmentDAOImpl.getAllAppointmentsByContactID(selectedID);

        schedule_tbl.setItems(contAppts);

        app_ID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        start.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        end.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        cust_ID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }

    /**
     * This method gets the count of selected location's appointments.
     * @param actionEvent This is an Event representing some type of action.
     * @throws SQLException
     */
    public void on_location_opt(ActionEvent actionEvent) throws SQLException {
        String selectedLoc = location_opt.getSelectionModel().getSelectedItem().toString();
        int appSum = AppointmentDAOImpl.getAllAppointmentsByLoc(selectedLoc).size();


        location_lbl.setVisible(true);
        location_lbl.setText(selectedLoc + " - " + appSum);
    }

    /**
     * This method takes the user to menu when 'Go Back' button is clicked.
     * @param actionEvent This is an Event representing some type of action.
     * @throws IOException
     */
    public void on_back_btn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("menu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This method gets the count of selected month and type's appointments.
     * @return String that contains the selected month, type, and count of appointments.
     * @throws SQLException
     */
    public String getResult() throws SQLException {
        String result = "Please select month and type.";

        try {

            if((month_opt.getValue() != null) && (type_opt.getValue() != null)){
                result = month_opt.getSelectionModel().getSelectedItem().toString() + " - " + type_opt.getSelectionModel().getSelectedItem().toString() + " - " ;
            }

        } catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Input");
            alert.setContentText("Please select month and type.");
            alert.showAndWait();
        }

        Month selectedMonth = (Month)month_opt.getSelectionModel().getSelectedItem();
        int monthValue = selectedMonth.getValue();
        System.out.println(monthValue);
        int appSum = AppointmentDAOImpl.getAllAppointmentsByMonthType(monthValue, type_opt.getSelectionModel().getSelectedItem().toString()).size();
        result = result + appSum;

        return result;
    }

    /**
     * This method sets the result label to be visible and displays the result string.
     * @param actionEvent This is an Event representing some type of action.
     * @throws SQLException
     */
    public void on_display_btn(ActionEvent actionEvent) throws SQLException {
        month_type_lbl.setVisible(true);
        month_type_lbl.setText(getResult());
    }
}
