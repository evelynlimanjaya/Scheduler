package controller;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerRecordController implements Initializable {
    public TableColumn cust_id;
    public TableColumn cust_name;
    public TableColumn address;
    public TableColumn postal_code;
    public TableColumn phone;
    public TableColumn create_date;
    public TableColumn created_by;
    public TableColumn last_update;
    public TableColumn last_updated_by;
    public TableColumn division_id;
    public TableView cust_table;
    public Button back_btn;
    public Button add_btn;
    public Button update_btn;
    public Button delete_btn;
    public Label delete_label;
    ObservableList<Customer> customersList = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshTable();
    }

    public void on_back_btn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("menu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void on_add_btn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("add_customer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void on_delete_btn(ActionEvent actionEvent) {
        Alert alertDelete = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the selected customer. Do you want to proceed?");
        Optional<ButtonType> result = alertDelete.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            Customer selected = (Customer) cust_table.getSelectionModel().getSelectedItem();
            int selectedID = selected.getCustomerID();

            DBConnection.getConnection();
            String sqlStatement = "DELETE FROM customers WHERE Customer_ID = " + selectedID;
            Query.makeQuery(sqlStatement);

            refreshTable();

            delete_label.setVisible(true);
        }
    }

    private void refreshTable(){
        try {
            customersList = CustomerDAOImpl.getAllCustomers();
        } catch (Exception e) {
            System.out.println(e);
        }

        cust_table.setItems(customersList);

        cust_id.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        cust_name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        postal_code.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        create_date.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        created_by.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        last_update.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        last_updated_by.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        division_id.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
    }

    public void on_update_btn(ActionEvent actionEvent) throws IOException {
        try {
            Customer selected = (Customer) cust_table.getSelectionModel().getSelectedItem();
            UpdateCustomerController.passData(selected);

            FXMLLoader fxmlLoader = new FXMLLoader(SchedulerMain.class.getResource("update_customer.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Update Customer");
            stage.setScene(scene);
            stage.show();
        } catch (LoadException l){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Customer is Selected");
            alert.setContentText("Please select a customer from the table." );
            alert.showAndWait();
        }

    }
}
