package controller;

import DAO.DBConnection;
import com.elimanjaya.scheduler.LambdaDisplayScene;
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

/**
 * This class controls the GUI elements of menu.fxml.
 */
public class MenuController implements Initializable {
    public Button customers_btn;
    public Button appointments_btn;
    public Button log_out_btn;
    public Button exit_btn;
    public Button summary_btn;
    LambdaDisplayScene displayScene = (s, ae, w, h) -> {
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource(s));
        Stage stage = (Stage)((Node)ae.getSource()).getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), w, h);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Customer Record");
        stage.setScene(scene);
        stage.show();
    };

    /**
     * This method is used to initialize the controller.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * This method takes the user to customer record.
     * LAMBDA EXPRESSION is used to simplify the code to go to a new scene.
     * @param actionEvent This is an Event representing some type of action.
     * @throws IOException
     */
    public void on_customers_btn(ActionEvent actionEvent) throws IOException {
        displayScene.goToNewScene("customer_record.fxml", actionEvent, 1500, 400);
    }

    /**
     * This method takes the user to appointment record.
     * LAMBDA EXPRESSION is used to simplify the code to go to a new scene.
     * @param actionEvent This is an Event representing some type of action.
     * @throws IOException
     */
    public void on_appointments_btn(ActionEvent actionEvent) throws IOException {
        displayScene.goToNewScene("appointment_record.fxml", actionEvent, 1800, 400);
    }

    /**
     * This method takes the user to appointment record.
     * LAMBDA EXPRESSION is used to simplify the code to go to a new scene.
     * @param actionEvent This is an Event representing some type of action.
     * @throws IOException
     */
    public void on_summary_btn(ActionEvent actionEvent) throws IOException {
        displayScene.goToNewScene("appt_summary_report.fxml", actionEvent, 800, 500);

    }

    /**
     * This method takes the user to login form.
     * LAMBDA EXPRESSION is used to simplify the code to go to a new scene.
     * @param actionEvent This is an Event representing some type of action.
     * @throws IOException
     */
    public void on_log_out_btn(ActionEvent actionEvent) throws IOException {
        displayScene.goToNewScene("login.fxml", actionEvent, 600, 400);
    }

    /**
     * This method exits the application.
     * @param actionEvent This is an Event representing some type of action.
     */
    public void on_exit_btn(ActionEvent actionEvent) {
        DBConnection.closeConnection();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }


}
