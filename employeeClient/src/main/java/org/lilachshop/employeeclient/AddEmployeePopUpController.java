package org.lilachshop.employeeclient;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.entities.Employee;
import org.lilachshop.entities.Role;
import org.lilachshop.entities.Store;

public class AddEmployeePopUpController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField employeeFirstNameTF;

    @FXML
    private TextField employeeLastNameTF;

    @FXML
    private TextField employeeTF;

    @FXML
    private ChoiceBox<Role> roleChoiceBox;

    @FXML
    private ChoiceBox<Store> storeChoiceBox;

    private EventBus bus;

    public EventBus getBus() {
        return bus;
    }

    @FXML
    void initialize() {
        assert employeeFirstNameTF != null : "fx:id=\"employeeFirstNameTF\" was not injected: check your FXML file 'AddEmployeePopUp.fxml'.";
        assert employeeLastNameTF != null : "fx:id=\"employeeLastNameTF\" was not injected: check your FXML file 'AddEmployeePopUp.fxml'.";
        assert employeeTF != null : "fx:id=\"employeeTF\" was not injected: check your FXML file 'AddEmployeePopUp.fxml'.";
        assert roleChoiceBox != null : "fx:id=\"roleChoiceBox\" was not injected: check your FXML file 'AddEmployeePopUp.fxml'.";
        assert storeChoiceBox != null : "fx:id=\"storeChoiceBox\" was not injected: check your FXML file 'AddEmployeePopUp.fxml'.";
        EventBus.getDefault().register(this);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bus = EventBus.builder().build();
    }
}

