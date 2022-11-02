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
import model.Country;
import model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class controls the GUI elements of add_customer.fxml.
 */
public class AddCustomerController implements Initializable {
    public TextField name;
    public TextField address;
    public TextField postal_code;
    public TextField phone;
    public ComboBox country;
    public ComboBox first_level_div;
    public Button back_btn;
    public Button add_btn;
    public Label id_label;
    ObservableList<Country> countries = null;

    /**
     * This method is used to initialize the controller.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            countries = CountriesDAOImpl.getAllCountries();

        } catch (Exception e) {
            System.out.println(e);

        }

        country.setItems(countries);

        try {

            id_label.setText(Integer.toString(CustomerDAOImpl.getLargestID() + 1));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * This method defines what happens when the user selects an option in country combo box.
     * @param actionEvent This is an Event representing some type of action.
     */
    public void on_country(ActionEvent actionEvent) {
        ObservableList<FirstLevelDivision> divisions = null;
        Country selectedCountry = (Country) country.getSelectionModel().getSelectedItem();
        try {
            divisions = FirstLevelDivDAOImpl.getAllDivisionsByCountryID(selectedCountry.getCountryID());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        first_level_div.setItems(divisions);
    }

    /**
     * This method takes the user to customer record when 'Go Back' button is clicked.
     * @param actionEvent This is an Event representing some type of action.
     * @throws IOException
     */
    public void on_back_btn(ActionEvent actionEvent) throws IOException {
        Alert alertDelete = new Alert(Alert.AlertType.CONFIRMATION, "You will lose all changes if you go back now. Do you want to proceed?");
        Optional<ButtonType> result = alertDelete.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("customer_record.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1500, 400);
            stage.setTitle("Customer Record");
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
     * This method adds a new customer to the database when 'Add' button is clicked.
     * @param actionEvent This is an Event representing some type of action.
     */
    public void on_add_btn(ActionEvent actionEvent) {
        try {
            int customerID = Integer.parseInt(id_label.getText());
            String customerName = name.getText();
            String addressInput = address.getText();
            String postalCode = postal_code.getText();
            String phoneInput = phone.getText();
            LocalDateTime createDate = LocalDateTime.now();
            String createdBy = LoginController.userNameInput;
            LocalDateTime lastUpdate = LocalDateTime.now();
            String lastUpdatedBy = LoginController.userNameInput;
            FirstLevelDivision selectedDivision = (FirstLevelDivision)first_level_div.getSelectionModel().getSelectedItem();
            int divID = selectedDivision.getDivisionID();
            String sql = "INSERT INTO client_schedule.customers  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, customerID);
            ps.setString(2, customerName);
            ps.setString(3, addressInput);
            ps.setString(4, postalCode);
            ps.setString(5, phoneInput);
            ps.setTimestamp(6, Timestamp.valueOf(createDate));
            ps.setString(7, createdBy);
            ps.setTimestamp(8, Timestamp.valueOf(lastUpdate));
            ps.setString(9, lastUpdatedBy);
            ps.setInt(10, divID);

            ps.execute();

            FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("customer_record.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1500, 400);
            stage.setTitle("Customer Record");
            stage.setScene(scene);
            stage.show();
        } catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Please input the correct values:\n - Customer Name : maximum 50 characters\n - Address : maximum 100 characters\n - Postal Code : maximum 50 characters\n - Phone : maximum 50 characters" );
            alert.showAndWait();
        } catch (NullPointerException npe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Please input the correct values:\n - Country and First-level Division have to be selected" );
            alert.showAndWait();
        } catch (IOException ioe){
            System.out.println(ioe);
        }
    }
}
