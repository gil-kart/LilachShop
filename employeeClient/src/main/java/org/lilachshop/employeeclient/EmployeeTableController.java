package org.lilachshop.employeeclient;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;

import com.mysql.cj.util.StringUtils;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableLongValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.entities.*;
import org.lilachshop.panels.*;

public class EmployeeTableController implements Initializable {

    private Panel panel;

    private SystemManagerPanel sPanel;

    private Parent popUpRoot;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<Employee, String> userNameCol;

    @FXML
    private TableColumn<Employee, Long> employeeID;

    @FXML
    private TableColumn<Employee, String> passwordCol;

    @FXML
    private TableColumn<Employee, Role> roleCol;

    @FXML
    private TableColumn<Employee, Store> storeCol;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private Button updateBtn;

    private static EventBus popUpBus;

    private ObservableList<Employee> listOfEmployees;
    private List<Store> stores;

    private void presentRowSelected() throws IOException {
        if (listOfEmployees.isEmpty()) {
            return;
        }
        ObservableList<Employee> employees = employeeTable.getSelectionModel().getSelectedItems();
        EventBus.getDefault().post(new EmployeeEvent(employees.get(0)));

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Selected Employee");
        stage.setScene(new Scene(popUpRoot));
        stage.show();
    }

    @Subscribe
    public void onRecieveEmployees(List<Employee> employees) {
        System.out.println("got employees from server, # of employees is: " + employees.size());
        Platform.runLater(() -> {
            setEmployeeRows(employees);
        });
    }

    @Subscribe
    public void onRecieveStores(List<Store> stores) {
        this.stores = stores.size() > 0 ? stores : null;
    }

    private void setEmployeeRows(List<Employee> employees) {
        if (employees.isEmpty())
            return;
        listOfEmployees = FXCollections.observableArrayList();
        listOfEmployees.addAll(employees);
        employeeTable.setEditable(true);
        employeeTable.setItems(listOfEmployees);
    }

    private void setPlaceHolder() {
        if (employeeTable.getItems().isEmpty()) {
            employeeTable.setPlaceholder(new Label("No rows to display"));
        }
    }

    private Set<String> getStoreNames() {
        List<String> storeNames = new LinkedList<>();
        stores.forEach((s) -> {
            storeNames.add(s.getStoreName());
        });
        return new HashSet<>(storeNames);
    }

    private Set<Store> getStoreSet() {
        return new HashSet<>(stores);
    }

    @FXML
    void onAddClicked(ActionEvent event) {
    }

    @FXML
    void onDeleteClicked(ActionEvent event) {
        Employee employee = employeeTable.getSelectionModel().getSelectedItem();
        List<Employee> employeesCopy = new LinkedList<>(employeeTable.getItems());
        employeesCopy.remove(employee);
        employeeTable.getItems().clear();
        ObservableList<Employee> oListCopy = FXCollections.observableArrayList(employeesCopy);
        employeeTable.setItems(oListCopy);
        employeeTable.refresh();
        listOfEmployees = oListCopy;
        // todo send remove request here
    }

    @FXML
    void onUpdateClicked(ActionEvent event) {
        List<Employee> employeesToUpdate = listOfEmployees;
        sPanel.setAllEmployees(employeesToUpdate);
    }

    @FXML
    void initialize() {
        assert addBtn != null : "fx:id=\"addBtn\" was not injected: check your FXML file 'EmployeeTable.fxml'.";
        assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'EmployeeTable.fxml'.";
        assert userNameCol != null : "fx:id=\"employeeFirstName\" was not injected: check your FXML file 'EmployeeTable.fxml'.";
        assert employeeID != null : "fx:id=\"employeeID\" was not injected: check your FXML file 'EmployeeTable.fxml'.";
        assert passwordCol != null : "fx:id=\"employeeLastName\" was not injected: check your FXML file 'EmployeeTable.fxml'.";
        assert roleCol != null : "fx:id=\"employeeRole\" was not injected: check your FXML file 'EmployeeTable.fxml'.";
        assert storeCol != null : "fx:id=\"employeeStore\" was not injected: check your FXML file 'EmployeeTable.fxml'.";
        assert employeeTable != null : "fx:id=\"employeeTable\" was not injected: check your FXML file 'EmployeeTable.fxml'.";
        assert updateBtn != null : "fx:id=\"updateBtn\" was not injected: check your FXML file 'EmployeeTable.fxml'.";
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellsValueFactories();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEmployeePopUp.fxml"));
            popUpRoot = loader.load();
            AddEmployeePopUpController controller = loader.getController();
            controller.getBus().register(this);
        } catch (IOException e) {
            System.out.println("Unable to load pop up display.");
            e.printStackTrace();
        }
        panel = OperationsPanelFactory.createPanel(PanelEnum.SYSTEM_MANAGER, this);
        sPanel = (SystemManagerPanel) panel;
        sPanel.getAllEmployees();

        /*TableView stuff*/
//        employeeTable.setOnMouseClicked(e -> {
//            try {
//                presentRowSelected();
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//        });
        setPlaceHolder();
        var roleBoxCallback = new Callback<TableColumn<Employee, Role>, TableCell<Employee, Role>>() {
            @Override
            public TableCell<Employee, Role> call(TableColumn<Employee, Role> param) {
                ComboBox<Role> box = new ComboBox<>();
                TableCell<Employee, Role> cell = new TableCell<Employee, Role>() {
                    @Override
                    protected void updateItem(Role item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty)
                            setGraphic(null);
                        else {
                            setEditable(false);
                            setText(item.name());
                        }
                    }
                };

                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        var selModel = employeeTable.getSelectionModel();
                        if (!selModel.isSelected(cell.getTableRow().getIndex(), cell.getTableColumn()))
                            return;
                        if (event.getButton().equals(MouseButton.PRIMARY)) {
                            Employee employee = employeeTable.getSelectionModel().getSelectedItem();
                            if (employee != null)
                                box.setItems(FXCollections.observableArrayList(Role.values()));
                            cell.setEditable(true);
                        }
                        if (event.getClickCount() == 2 && cell.isEditable()) {
                            box.getSelectionModel().select(0);
                            cell.setText(null);
                            cell.setGraphic(box);
                        }

                    }
                });

                cell.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (event.getCode().equals(KeyCode.ENTER)) {
                            Employee employee = (Employee) employeeTable.getSelectionModel().getSelectedItem();
                            if (employee != null) {
                                employee.setRole(box.getSelectionModel().getSelectedItem());
                                cell.setEditable(false);
                                employeeTable.refresh();
                            }
                        } else if (event.getCode().equals(KeyCode.ESCAPE)) {
                            cell.setEditable(false);
                            employeeTable.refresh();
                            employeeTable.getSelectionModel().clearSelection();
                        }
                    }
                });
                return cell;
            }
        };
        roleCol.setCellFactory(roleBoxCallback);

        var storeBoxCallback = new Callback<TableColumn<Employee, Store>, TableCell<Employee, Store>>() {
            @Override
            public TableCell<Employee, Store> call(TableColumn<Employee, Store> param) {
                ComboBox<Store> box = new ComboBox<>();
                TableCell<Employee, Store> cell = new TableCell<Employee, Store>() {
                    @Override
                    protected void updateItem(Store item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty)
                            setGraphic(null);
                        else {
                            setEditable(false);
                            setText(item.getStoreName());
                        }
                    }
                };

                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        var selModel = employeeTable.getSelectionModel();
                        if (!selModel.isSelected(cell.getTableRow().getIndex(), cell.getTableColumn()))
                            return;
                        if (event.getButton().equals(MouseButton.PRIMARY)) {
                            Employee employee = employeeTable.getSelectionModel().getSelectedItem();
                            if (employee != null) {
                                box.setItems(FXCollections.observableArrayList(getStoreSet()));
                            }
                            cell.setEditable(true);
                        }
                        if (event.getClickCount() == 2 && cell.isEditable()) {
                            box.getSelectionModel().select(0);
                            cell.setText(null);
                            cell.setGraphic(box);
                        }

                    }
                });

                cell.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (event.getCode().equals(KeyCode.ENTER)) {
                            Employee employee = (Employee) employeeTable.getSelectionModel().getSelectedItem();
                            if (employee != null) {
                                employee.setStore(box.getSelectionModel().getSelectedItem());
                                cell.setEditable(false);
                                employeeTable.refresh();
                            }
                        } else if (event.getCode().equals(KeyCode.ESCAPE)) {
                            cell.setEditable(false);
                            employeeTable.refresh();
                            employeeTable.getSelectionModel().clearSelection();
                        }
                    }
                });
                return cell;
            }
        };
        storeCol.setCellFactory(storeBoxCallback);

        var usernameCallback = new Callback<TableColumn<Employee, String>, TableCell<Employee, String>>() {
            String tempString;

            @Override
            public TableCell<Employee, String> call(TableColumn<Employee, String> param) {
                TextField usernameTF = new TextField();
                TableCell<Employee, String> cell = new TableCell<Employee, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty)
                            setGraphic(null);
                        else {
                            setEditable(false);
                            setText(item);
                        }
                    }
                };

                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            var selModel = employeeTable.getSelectionModel();
                            if (!selModel.isSelected(cell.getTableRow().getIndex(), cell.getTableColumn()))
                                return;

                            if (event.getButton().equals(MouseButton.PRIMARY)) {
                                tempString = cell.getItem();
                                cell.setEditable(true);
                            }
                            if (event.getClickCount() == 2 && cell.isEditable()) {
                                cell.setText(null);
                                cell.setGraphic(usernameTF);
                            }
                        } catch (Exception ignored) {
                        }
                    }
                });

                cell.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (event.getCode().equals(KeyCode.ENTER)) {
                            Employee employee = (Employee) employeeTable.getSelectionModel().getSelectedItem();
                            if (employee != null) {
                                employee.setUserName(usernameTF.getText());
                                cell.setEditable(false);
                                employeeTable.refresh();
                            }
                        } else if (event.getCode().equals(KeyCode.ESCAPE)) {
                            cell.setEditable(false);
                            employeeTable.refresh();
                            employeeTable.getSelectionModel().clearSelection();
                        }
                    }
                });
                return cell;
            }
        };
        userNameCol.setCellFactory(usernameCallback);

        var passwordCallback = new Callback<TableColumn<Employee, String>, TableCell<Employee, String>>() {
            String tempString;

            @Override
            public TableCell<Employee, String> call(TableColumn<Employee, String> param) {
                TextField passwordTF = new TextField();
                TableCell<Employee, String> cell = new TableCell<Employee, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty)
                            setGraphic(null);
                        else {
                            setEditable(false);
                            setText(item);
                        }
                    }
                };

                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            var selModel = employeeTable.getSelectionModel();
                            if (!selModel.isSelected(cell.getTableRow().getIndex(), cell.getTableColumn()))
                                return;

                            if (event.getButton().equals(MouseButton.PRIMARY)) {
                                tempString = cell.getItem();
                                cell.setEditable(true);
                            }
                            if (event.getClickCount() == 2 && cell.isEditable()) {
                                cell.setText(null);
                                cell.setGraphic(passwordTF);
                            }
                        } catch (Exception ignored) {
                        }
                    }
                });

                cell.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (event.getCode().equals(KeyCode.ENTER)) {
                            Employee employee = (Employee) employeeTable.getSelectionModel().getSelectedItem();
                            if (employee != null) {
                                employee.setUserPassword(passwordTF.getText());
                                cell.setEditable(false);
                                employeeTable.refresh();
                            }
                        } else if (event.getCode().equals(KeyCode.ESCAPE)) {
                            cell.setEditable(false);
                            employeeTable.refresh();
                            employeeTable.getSelectionModel().clearSelection();
                        }
                    }
                });
                return cell;
            }
        };
        passwordCol.setCellFactory(passwordCallback);

        /*******************/

        /*Catalog catalog = new Catalog();
        Store store = new Store("address", "storename1", catalog, null, null);
        Store store2 = new Store("address", "storename2", catalog, null, null);
        stores = new LinkedList<>(Arrays.asList(store, store2));
        Employee employee = new Employee(store, Role.STORE_EMPLOYEE, "Benny", "Blanco");
//        employee.setId(1L);
        Employee employee2 = new Employee(store2, Role.STORE_MANAGER, "Humus", "Sason");
//        employee2.setId(2L);
        List<Employee> dummyEmployees = new LinkedList<>(Arrays.asList(employee, employee2));
        setEmployeeRows(dummyEmployees);*/
    }

    private void setCellsValueFactories() {
        employeeID.setCellValueFactory(new PropertyValueFactory<Employee, Long>("id"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("userName"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("userPassword"));
        roleCol.setCellValueFactory(new PropertyValueFactory<Employee, Role>("role"));
        storeCol.setCellValueFactory(new PropertyValueFactory<Employee, Store>("store"));
    }
}
