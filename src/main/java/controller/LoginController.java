package controller;

import DAO.AppointmentDAOImpl;
import DAO.UserDaoImpl;
import com.elimanjaya.scheduler.LambdaErrorMsg;
import com.elimanjaya.scheduler.SchedulerMain;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import model.User;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class controls the GUI elements of login.fxml.
 */
public class LoginController implements Initializable {
    public TextField user_name_input;
    public TextField password_input;
    public Button login_btn;
    public Label title;
    public Label timezone;
    public Label zone_ID;
    public Label username_lbl;
    public Label password_lbl;
    public Label error_lbl;
    public ResourceBundle rb;
    public static String userNameInput;


    /**
     * This method is used to initialize the controller.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Locale.setDefault(new Locale("fr"));
        rb = ResourceBundle.getBundle("com.elimanjaya.scheduler/language", Locale.getDefault());
        System.out.println(Locale.getDefault());
        System.out.println(Locale.getDefault().getLanguage());

        if(Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr")){
            System.out.println(rb.getString("login"));
            System.out.println("success");
            title.setText(rb.getString("title"));
            username_lbl.setText(rb.getString("username"));
            password_lbl.setText(rb.getString("password"));
            user_name_input.setPromptText(rb.getString("username"));
            password_input.setPromptText(rb.getString("password"));
            timezone.setText(rb.getString("timezone"));
            zone_ID.setText(String.valueOf(ZoneId.systemDefault()));
            login_btn.setText(rb.getString("login"));
        } else {
            System.out.println("Locale not found.");
        }
    }

    /**
     * This method checks the username and password then accept or refuse login based on the result.
     * LAMBDA EXPRESSION is used inside this method to display the error message. The usage of lambda expression helps simplify the repeated process of displaying the message.
     * @param actionEvent This is an Event representing some type of action.
     */
    public void on_login_btn(ActionEvent actionEvent) {
        try{
            LambdaErrorMsg errorMsg = () -> {error_lbl.setVisible(true);
                error_lbl.setText(rb.getString("error"));
            };

            LocalDateTime current = LocalDateTime.now();
            ObservableList<Appointment> userAppts;
            boolean within15Minutes = false;

            userNameInput = user_name_input.getText();
            String passwordInput = password_input.getText();
            User user = UserDaoImpl.getUser(userNameInput);

            String filename = "login_activity.txt";
            FileWriter fw = new FileWriter(filename, true);
            PrintWriter pw = new PrintWriter(fw);

            LocalDateTime UTCNow = LocalDateTime.now(ZoneId.of("UTC"));

            if(user == null){
                pw.println("An empty username was submitted and gave invalid log in at " + UTCNow + " UTC");
                pw.close();
                errorMsg.displayErrorMessage();
            }else if(user.getUserName().equals(userNameInput) && user.getPassword().equals(passwordInput)){
                try {
                    userAppts = AppointmentDAOImpl.getAllAppointmentsByUserID(UserDaoImpl.getUserID(LoginController.userNameInput));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                pw.println("User " + userNameInput + " succesfully logged in at " + UTCNow + " UTC");
                pw.close();
                System.out.println(LoginController.userNameInput);
                for(Appointment a: userAppts){
                    long timeDifference = ChronoUnit.MINUTES.between(a.getStartDateTime(), current);
                    System.out.println("Time difference:" + timeDifference);
                    if(timeDifference >= 0 && timeDifference <= 15){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Appointment");
                        alert.setContentText("A meeting has started: \n - Appointment ID: " + a.getAppointmentID() + "\n - Date: " + a.getStartDateTime().toLocalDate().toString() + "\n - Start time: " + a.getStartDateTime().toLocalTime().toString() + "\n - End time: " + a.getEndDateTime().toLocalTime().toString());
                        alert.showAndWait();
                        within15Minutes = true;
                        System.out.println("Check 1");
                    } else if (timeDifference < 0 && timeDifference >= -15) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Appointment");
                        alert.setContentText("A meeting is coming up: \n - Appointment ID: " + a.getAppointmentID() + "\n - Date: " + a.getStartDateTime().toLocalDate().toString() + "\n - Start time: " + a.getStartDateTime().toLocalTime().toString() + "\n - End time: " + a.getEndDateTime().toLocalTime().toString());
                        alert.showAndWait();
                        within15Minutes = true;
                        System.out.println("Check 2");
                    }
                }

                if(within15Minutes == false){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Appointment");
                    alert.setContentText("No upcoming appointment.");
                    alert.showAndWait();
                    System.out.println("Check 3");
                }

                FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("menu.fxml"));
                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setTitle("Menu");
                stage.setScene(scene);
                stage.show();
                System.out.println("Login successful");
                error_lbl.setVisible(false);

            } else {
                pw.println("User " + userNameInput + " gave invalid log in at " + UTCNow + " UTC");
                pw.close();
                errorMsg.displayErrorMessage();
            }


        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
