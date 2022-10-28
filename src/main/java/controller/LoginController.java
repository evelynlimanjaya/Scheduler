package controller;

import DAO.UserDaoImpl;
import com.elimanjaya.scheduler.SchedulerMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.net.URL;
import java.time.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Locale.setDefault(new Locale("fr"));
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

    public void on_user_name_input(ActionEvent actionEvent) {
    }

    public void on_password_input(ActionEvent actionEvent) {
    }

    public void on_login_btn(ActionEvent actionEvent) {
        try{
            userNameInput = user_name_input.getText();
            String passwordInput = password_input.getText();
            User user = UserDaoImpl.getUser(userNameInput);

            if(user == null){
                error_lbl.setVisible(true);
                error_lbl.setText(rb.getString("error"));
            }else if(user.getUserName().equals(userNameInput) && user.getPassword().equals(passwordInput)){
                FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("menu.fxml"));
                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setTitle("Main Form");
                stage.setScene(scene);
                stage.show();
                System.out.println("Login successful");
                error_lbl.setVisible(false);

            } else {
                error_lbl.setVisible(true);
                error_lbl.setText(rb.getString("error"));
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
