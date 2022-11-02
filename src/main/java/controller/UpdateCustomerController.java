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
import model.Customer;
import model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class controls the GUI elements of update_customer.fxml.
 */
public class UpdateCustomerController implements Initializable {
    public TextField name;
    public TextField address;
    public TextField postal_code;
    public TextField phone;
    public ComboBox country;
    public ComboBox first_level_div;
    public Button update_btn;
    public Button back_btn;
    public Label id_label;

    ObservableList<Country> countries = null;

    private static Customer retrievedCust = null;

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
            id_label.setText(Integer.toString(retrievedCust.getCustomerID()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        name.setText(retrievedCust.getCustomerName());
        address.setText(retrievedCust.getAddress());
        postal_code.setText(retrievedCust.getPostalCode());
        phone.setText(retrievedCust.getPhone());
        int retrievedDivisionID = retrievedCust.getDivisionID();
        int retrievedCountryID = 0;
        String countryName;

        DBConnection.getConnection();
        String sqlStatement = "SELECT Country_ID FROM first_level_divisions WHERE Division_ID = " + retrievedDivisionID;
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        try {
            while (result.next()){
                retrievedCountryID = result.getInt("Country_ID");
            }
        } catch (SQLException e){
            System.out.println(e);
        }

        for(Country c : countries){
            if(c.getCountryID() == retrievedCountryID){
                country.setValue(c);
            }
        }

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
        for(FirstLevelDivision d : divisions){
            if(d.getDivisionID() == retrievedDivisionID){
                first_level_div.setValue(d);
            }
        }

    }

    /**
     * This method retrieves the customer passed from another class used to get the selected customer from a table view.
     * @param c Customer object that is passed from another class.
     */
    public static void passData (Customer c) {
        retrievedCust = c;
    }

    /**
     * This method populates the first-level division combo box with the divisions of the selected country.
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
     * This method updates the customer data in the database when 'Update' button is clicked.
     * @param actionEvent This is an Event representing some type of action.
     */
    public void on_update_btn(ActionEvent actionEvent) {
        try {
            int customerID = Integer.parseInt(id_label.getText());
            String customerName = name.getText();
            String addressInput = address.getText();
            String postalCode = postal_code.getText();
            String phoneInput = phone.getText();
            LocalDateTime lastUpdate = LocalDateTime.now();
            String lastUpdatedBy = LoginController.userNameInput;
            FirstLevelDivision selectedDivision = (FirstLevelDivision)first_level_div.getSelectionModel().getSelectedItem();
            int divID = selectedDivision.getDivisionID();

            DBConnection.getConnection();
            String sql = "UPDATE client_schedule.customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_by = ?, Division_ID = ? WHERE Customer_ID = ?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, customerName);
            ps.setString(2, addressInput);
            ps.setString(3, postalCode);
            ps.setString(4, phoneInput);
            ps.setTimestamp(5, Timestamp.valueOf(lastUpdate));
            ps.setString(6, lastUpdatedBy);
            ps.setInt(7, divID);
            ps.setInt(8, customerID);

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


}
