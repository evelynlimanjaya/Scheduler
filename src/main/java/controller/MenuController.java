package controller;

import DAO.DBConnection;
import com.elimanjaya.scheduler.SchedulerMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public Button customers_btn;
    public Button appointments_btn;
    public Button log_out_btn;
    public Button exit_btn;

    public void on_customers_btn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("customer_record.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1500, 400);
        stage.setTitle("Customer Record");
        stage.setScene(scene);
        stage.show();
    }

    public void on_appointments_btn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("appointment_record.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1800, 400);
        stage.setTitle("Appointment Record");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void on_log_out_btn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("login.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Login Form");
        stage.setScene(scene);
        stage.show();
    }

    public void on_exit_btn(ActionEvent actionEvent) {
        DBConnection.closeConnection();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
