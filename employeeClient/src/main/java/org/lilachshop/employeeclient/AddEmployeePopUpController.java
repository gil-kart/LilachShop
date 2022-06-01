package org.lilachshop.employeeclient;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.commonUtils.DisableOnEmptyListener;
import org.lilachshop.entities.Employee;
import org.lilachshop.entities.Role;
import org.lilachshop.entities.Store;
import org.lilachshop.events.AddEmployeeEvent;
import org.lilachshop.events.StoreEvent;

public class AddEmployeePopUpController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField employeeUsernameTF;

    @FXML
    private TextField employeePasswordTF;

    @FXML
    private ChoiceBox<Role> roleChoiceBox;

    @FXML
    private ChoiceBox<Store> storeChoiceBox;

    @FXML
    private Button resetBtn;

    @FXML
    private Button addBtn;

    private Stage stage;

    private static EventBus bus = null;

    public EventBus getBus() {
        return bus;
    }

    @FXML
    void onClickAdd(ActionEvent event) {
        Store store = storeChoiceBox.getSelectionModel().getSelectedItem();
        Role role = roleChoiceBox.getSelectionModel().getSelectedItem();
        String username = employeeUsernameTF.getText();
        String password = employeePasswordTF.getText();
        Employee employee = new Employee(store, role, username, password);
        bus.post(new AddEmployeeEvent(employee));
        stage = (Stage) addBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onClickReset(ActionEvent event) {
        employeeUsernameTF.setText("");
        employeePasswordTF.setText("");
        roleChoiceBox.getSelectionModel().clearSelection();
        storeChoiceBox.getSelectionModel().clearSelection();
    }

    @Subscribe
    public void onReceiveStores(StoreEvent event) {
        List<Store> stores = event.getStores();
        Platform.runLater(() -> {
            storeChoiceBox.setItems(FXCollections.observableArrayList(stores));
        });
    }

    @Subscribe
    public void onClearScreen(String msg) {
        if (msg.equals("Clear widgets")) {
            Platform.runLater(() -> onClickReset(null));
        }
    }

    @FXML
    void initialize() {
        assert addBtn != null : "fx:id=\"addBtn\" was not injected: check your FXML file 'AddEmployeePopUp.fxml'.";
        assert employeeUsernameTF != null : "fx:id=\"employeeFirstNameTF\" was not injected: check your FXML file 'AddEmployeePopUp.fxml'.";
        assert employeePasswordTF != null : "fx:id=\"employeeLastNameTF\" was not injected: check your FXML file 'AddEmployeePopUp.fxml'.";
        assert resetBtn != null : "fx:id=\"resetBtn\" was not injected: check your FXML file 'AddEmployeePopUp.fxml'.";
        assert roleChoiceBox != null : "fx:id=\"roleChoiceBox\" was not injected: check your FXML file 'AddEmployeePopUp.fxml'.";
        assert storeChoiceBox != null : "fx:id=\"storeChoiceBox\" was not injected: check your FXML file 'AddEmployeePopUp.fxml'.";
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bus = bus == null ? EventBus.builder().build() : bus;
        if (!bus.isRegistered(this))
            bus.register(this);

        onClickReset(null);
        addBtn.setDisable(true);

        addBtn.disableProperty().bind(Bindings.isEmpty(employeeUsernameTF.textProperty()).
                or(Bindings.isEmpty(employeePasswordTF.textProperty())).or(roleChoiceBox.valueProperty().isNull()).
                or(storeChoiceBox.valueProperty().isNull()));

        roleChoiceBox.setItems(FXCollections.observableArrayList(Arrays.asList(Role.values())));
    }
}

