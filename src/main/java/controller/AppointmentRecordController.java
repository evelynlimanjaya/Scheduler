package controller;

import DAO.AppointmentDAOImpl;
import DAO.CustomerDAOImpl;
import DAO.DBConnection;
import DAO.Query;
import com.elimanjaya.scheduler.SchedulerMain;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class controls the GUI elements of appointment_record.fxml.
 */
public class AppointmentRecordController implements Initializable {
    public Button back_btn;
    public Button add_btn;
    public Button update_btn;
    public Button delete_btn;
    public RadioButton all_btn;
    public RadioButton week_btn;
    public RadioButton month_btn;
    public TableColumn app_id;
    public TableColumn title;
    public TableColumn description;
    public TableColumn location;
    public TableColumn type;
    public TableColumn start;
    public TableColumn end;
    public TableColumn create_date;
    public TableColumn created_by;
    public TableColumn last_update;
    public TableColumn last_updated_by;
    public TableColumn cust_id;
    public TableColumn user_id;
    public TableColumn contact_id;

    public ObservableList<Appointment> appointmentsList = null;
    public TableView app_table;
    public Label delete_label;

    /**
     * This method is used to initialize the controller.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();
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
     * This method takes the user to the appropriate form to add appointment.
     * @param actionEvent This is an Event representing some type of action.
     * @throws IOException
     */
    public void on_add_btn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("add_appointment.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method takes the user to the appropriate form to update appointment.
     * @param actionEvent This is an Event representing some type of action.
     * @throws IOException
     */
    public void on_update_btn(ActionEvent actionEvent) throws IOException {
        try {
            Appointment selected = (Appointment) app_table.getSelectionModel().getSelectedItem();
            UpdateAppointmentController.passData(selected);

            FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("update_appointment.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 800, 400);
            stage.setTitle("Update Appointment");
            stage.setScene(scene);
            stage.show();
        } catch (LoadException l){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Appointment is Selected");
            alert.setContentText("Please select an appointment from the table." );
            alert.showAndWait();
        }

    }

    /**
     * This method deletes the selected appointment from the database.
     * @param actionEvent This is an Event representing some type of action.
     */
    public void on_delete_btn(ActionEvent actionEvent) {

        Alert alertDelete = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected customer. Do you want to proceed?");
        Optional<ButtonType> result = alertDelete.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            Appointment selected = (Appointment) app_table.getSelectionModel().getSelectedItem();
            int selectedID = selected.getAppointmentID();

            delete_label.setText("Appointment with Appointment ID: " + selectedID + " and type: " + selected.getType() + " was canceled.");
            delete_label.setVisible(true);

            DBConnection.getConnection();
            String sqlStatement = "DELETE FROM appointments WHERE Appointment_ID = " + selectedID;
            Query.makeQuery(sqlStatement);

            refreshTable();
        }
    }

    /**
     * This method shows all appointments in table view.
     * @param actionEvent This is an Event representing some type of action.
     */
    public void on_all_btn(ActionEvent actionEvent) {
        refreshTable();
    }

    /**
     * This method shows this week's appointments in table view.
     * @param actionEvent This is an Event representing some type of action.
     */
    public void on_week_btn(ActionEvent actionEvent) {
        refreshTableByWeek();
    }

    /**
     * This method shows this month's appointments in table view.
     * @param actionEvent This is an Event representing some type of action.
     */
    public void on_month_btn(ActionEvent actionEvent) {
        refreshTableByMonth();
    }

    /**
     * This method refreshes the table view to display all appointments.
     */
    private void refreshTable(){
        try {
            appointmentsList = AppointmentDAOImpl.getAllAppointments();
        } catch (Exception e) {
            System.out.println(e);
        }

        app_table.setItems(appointmentsList);

        app_id.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        start.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        end.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        create_date.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        created_by.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        last_update.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        last_updated_by.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        cust_id.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        user_id.setCellValueFactory(new PropertyValueFactory<>("userID"));
        contact_id.setCellValueFactory(new PropertyValueFactory<>("contactID"));
    }

    /**
     * This method refreshes the table view to display this week's appointments.
     */
    private void refreshTableByWeek(){
        try {
            appointmentsList = AppointmentDAOImpl.getAllAppointmentsWeek();
        } catch (Exception e) {
            System.out.println(e);
        }

        app_table.setItems(appointmentsList);

        app_id.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        start.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        end.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        create_date.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        created_by.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        last_update.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        last_updated_by.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        cust_id.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        user_id.setCellValueFactory(new PropertyValueFactory<>("userID"));
        contact_id.setCellValueFactory(new PropertyValueFactory<>("contactID"));
    }

    /**
     * This method refreshes the table view to display this month's appointments.
     */
    private void refreshTableByMonth(){
        try {
            appointmentsList = AppointmentDAOImpl.getAllAppointmentsMonth();
        } catch (Exception e) {
            System.out.println(e);
        }

        app_table.setItems(appointmentsList);

        app_id.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        start.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        end.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        create_date.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        created_by.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        last_update.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        last_updated_by.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        cust_id.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        user_id.setCellValueFactory(new PropertyValueFactory<>("userID"));
        contact_id.setCellValueFactory(new PropertyValueFactory<>("contactID"));
    }
}
