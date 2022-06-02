package org.lilachshop.employeeclient;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.lilachshop.entities.Employee;

public class CustomerTableController {

    public Employee employee;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> accountTypeCol;

    @FXML
    private TableColumn<?, ?> addressCol;

    @FXML
    private TableColumn<?, ?> creationDateCol;

    @FXML
    private TableColumn<?, ?> creditCardCol;

    @FXML
    private TableColumn<?, ?> customerIDCol;

    @FXML
    private TableColumn<?, ?> customerNameCol;

    @FXML
    private TableColumn<?, ?> cvcCol;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<?, ?> disabledCol;

    @FXML
    private TableView<?> employeeTable;

    @FXML
    private TableColumn<?, ?> expDateCol;

    @FXML
    private TableColumn<?, ?> passwordCol;

    @FXML
    private TableColumn<?, ?> phoneCol;

    @FXML
    private TableColumn<?, ?> storeCol;

    @FXML
    private Button updateBtn;

    @FXML
    private TableColumn<?, ?> usernameCol;

    @FXML
    void onDeleteClicked(ActionEvent event) {

    }

    @FXML
    void onUpdateClicked(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert accountTypeCol != null : "fx:id=\"accountTypeCol\" was not injected: check your FXML file 'CustomerTable.fxml'.";
        assert addressCol != null : "fx:id=\"addressCol\" was not injected: check your FXML file 'CustomerTable.fxml'.";
        assert creationDateCol != null : "fx:id=\"creationDateCol\" was not injected: check your FXML file 'CustomerTable.fxml'.";
        assert creditCardCol != null : "fx:id=\"creditCardCol\" was not injected: check your FXML file 'CustomerTable.fxml'.";
        assert customerIDCol != null : "fx:id=\"customerIDCol\" was not injected: check your FXML file 'CustomerTable.fxml'.";
        assert customerNameCol != null : "fx:id=\"customerNameCol\" was not injected: check your FXML file 'CustomerTable.fxml'.";
        assert cvcCol != null : "fx:id=\"cvcCol\" was not injected: check your FXML file 'CustomerTable.fxml'.";
        assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'CustomerTable.fxml'.";
        assert disabledCol != null : "fx:id=\"disabledCol\" was not injected: check your FXML file 'CustomerTable.fxml'.";
        assert employeeTable != null : "fx:id=\"employeeTable\" was not injected: check your FXML file 'CustomerTable.fxml'.";
        assert expDateCol != null : "fx:id=\"expDateCol\" was not injected: check your FXML file 'CustomerTable.fxml'.";
        assert passwordCol != null : "fx:id=\"passwordCol\" was not injected: check your FXML file 'CustomerTable.fxml'.";
        assert phoneCol != null : "fx:id=\"phoneCol\" was not injected: check your FXML file 'CustomerTable.fxml'.";
        assert storeCol != null : "fx:id=\"storeCol\" was not injected: check your FXML file 'CustomerTable.fxml'.";
        assert updateBtn != null : "fx:id=\"updateBtn\" was not injected: check your FXML file 'CustomerTable.fxml'.";
        assert usernameCol != null : "fx:id=\"usernameCol\" was not injected: check your FXML file 'CustomerTable.fxml'.";

    }

}
