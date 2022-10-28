package controller;

import DAO.AppointmentDAOImpl;
import DAO.CustomerDAOImpl;
import com.elimanjaya.scheduler.SchedulerMain;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentRecordController implements Initializable {
    public Button back_btn;
    public Button add_btn;
    public Button update_btn;
    public Button delete_btn;
    public RadioButton all_btn;
    public RadioButton week_btn;
    public RadioButton month_btn;
    public TableView cust_table;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();
    }

    public void on_back_btn(ActionEvent actionEvent) {
    }

    public void on_add_btn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("add_appointment.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    public void on_update_btn(ActionEvent actionEvent) {
    }

    public void on_delete_btn(ActionEvent actionEvent) {
    }

    public void on_all_btn(ActionEvent actionEvent) {
        refreshTable();
    }

    public void on_week_btn(ActionEvent actionEvent) {
        refreshTableByWeek();
    }

    public void on_month_btn(ActionEvent actionEvent) {
        refreshTableByMonth();
    }

    private void refreshTable(){
        try {
            appointmentsList = AppointmentDAOImpl.getAllAppointments();
        } catch (Exception e) {
            System.out.println(e);
        }

        cust_table.setItems(appointmentsList);

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

    private void refreshTableByWeek(){
        try {
            appointmentsList = AppointmentDAOImpl.getAllAppointmentsWeek();
        } catch (Exception e) {
            System.out.println(e);
        }

        cust_table.setItems(appointmentsList);

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

    private void refreshTableByMonth(){
        try {
            appointmentsList = AppointmentDAOImpl.getAllAppointmentsMonth();
        } catch (Exception e) {
            System.out.println(e);
        }

        cust_table.setItems(appointmentsList);

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
