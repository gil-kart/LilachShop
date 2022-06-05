package org.lilachshop.employeeclient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.entities.*;
import org.lilachshop.events.OrderEvent;
import org.lilachshop.panels.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderReportController implements Initializable {
    public Employee employee;
    private static Panel panel;
    Catalog catalog;
    List<Order> orders;
    List<Order> ordersFromAllStores;
    ObservableList<ItemSalesObservable> listOfObservableItems;
    @FXML
    private Label totalNumOfOrders;
    @FXML
    private Button newScreenBtn;
    @FXML
    private Label totalChainNumberOfOrders;

    @FXML
    private Label chooseStoreLabel;

    @FXML
    private TableColumn<ItemSalesObservable, Integer> ItemName;

    @FXML
    private TableColumn<ItemSalesObservable, Integer> OrderNumber;

    @FXML
    private TableColumn<ItemSalesObservable, Integer> Price;

    @FXML
    private DatePicker endDate;

    @FXML
    private Button okBtn;

    @FXML
    private DatePicker startDate;

    @FXML
    private ComboBox<String> storeList;

    @FXML
    private TableView<ItemSalesObservable> tableView;

    @FXML
    void onChangeStore(ActionEvent event) {
        String selectedStore = storeList.getSelectionModel().getSelectedItem();
        tableView.getItems().clear();
        startDate.setValue(null);
        endDate.setValue(null);
        //todo: get complaints from all stores
        if (selectedStore.equals("לילך הרצליה")) {
            ((StoreManagerPanel) panel).getStoreOrders(2);
            ((StoreManagerPanel) panel).getStoreCatalog(2);
        } else if (selectedStore.equals("לילך חיפה")) {
            ((StoreManagerPanel) panel).getStoreOrders(1);
            ((StoreManagerPanel) panel).getStoreCatalog(1);
        }
    }

    @FXML
    void updateBarChart(ActionEvent event) {
        LocalDate start = startDate.getValue();
        LocalDate end = endDate.getValue();
        if (start == null || end == null) {
            displayNullAlert();
            return;
        }
        if (end.isBefore(start)) {
            displayChronologyAlert();
            return;
        }
        List<ItemSalesObservable> observableItems = getObservalbeItems();
        listOfObservableItems = FXCollections.observableArrayList();
        listOfObservableItems.addAll(observableItems);
        tableView.setEditable(true);
        tableView.setItems(listOfObservableItems);
        if(DashBoardController.panelEnum.equals(PanelEnum.CHAIN_MANAGER)){
            calcTotalNumOfOrders(start, end);
        }
    }

    private void calcTotalNumOfOrders(LocalDate start, LocalDate end) {
        long todayOrderCounter = 0;
        long curTotalOrderForAllStores = 0;
        try {
            for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
                for (Order order : ordersFromAllStores) {
                    if (order.getCreationDate().equals(date)) {
                        todayOrderCounter += order.getAmountOfProducts();//1;
                    }
                }
                curTotalOrderForAllStores += todayOrderCounter;
                todayOrderCounter = 0;
            }
        }catch (Exception e){
            System.out.println("cant calculate number of orders");
        }
        totalNumOfOrders.setText(String.valueOf(curTotalOrderForAllStores));
    }

    private List<ItemSalesObservable> getObservalbeItems() {
        List<ItemSalesObservable> itemSalesObservables = new ArrayList<>();
        for (Item item : catalog.getItems()) {
            int numOfSales = getNumOfSalesForItem(item);
            ItemSalesObservable itemSalesObservable = new ItemSalesObservable(numOfSales, item.getPrice(), item.getName());

            itemSalesObservables.add(itemSalesObservable);
        }
        return itemSalesObservables;
    }

    private int getNumOfSalesForItem(Item item) { // todo: think of a more efficient way to calculate this
        int counter = 0;
        List<Order> ordersInDateRange = new ArrayList<>();
        LocalDate start = startDate.getValue();
        LocalDate end = endDate.getValue();
        // saving all orders in range in a list
        for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
            for (Order order : orders) {
                if (order.getCreationDate().equals(date)) {
                    ordersInDateRange.add(order);
                }
            }
        }
        //finding number of sales
        for (Order order : ordersInDateRange) {
            List<myOrderItem> orderItems = order.getItems();
            for (myOrderItem itemFromOrder : orderItems) {
                if (itemFromOrder.getName().equals(item.getName())) { //todo: right now comparing only product name, maybe should check more attributes
                    counter += itemFromOrder.getCount();
                }
            }
        }
        return counter;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        panel = OperationsPanelFactory.createPanel(DashBoardController.panelEnum, EmployeeApp.getSocket(), this);
//        panel = OperationsPanelFactory.createPanel(PanelEnum.STORE_MANAGER, this);

        storeList.getItems().addAll("לילך חיפה", "לילך תל אביב", "לילך הרצליה");
        storeList.promptTextProperty().set("לילך חיפה");
        ItemName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        OrderNumber.setCellValueFactory(new PropertyValueFactory<>("numOfSales"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }

    private void displayChronologyAlert() {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setHeaderText("יש לבחור תאריך התחלה מוקדם יותר מתאריך הסיום");
        a.setTitle("בחירת טווח תאריכים");
        a.setContentText("");
        a.show();
    }

    private void displayNullAlert() {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setHeaderText("יש לבחור תאריך התחלה וסיום!");
        a.setTitle("בחירת טווח תאריכים");
        a.setContentText("");
        a.show();
    }

    @FXML
    void onNewScreenBtnClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderReport.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @Subscribe
    public void handleMessageFromClient(Catalog catalog) {
        this.catalog = catalog;
    }

    @Subscribe
    public void handleMessageFromClient(List<Order> orders) {
        this.orders = orders;
    }

    @Subscribe
    public void handleMessageAllOrdersFromClient(OrderEvent orderEvent) {
        this.ordersFromAllStores = orderEvent.getOrders();
    }

    public void setData(long storeId) {
        if (DashBoardController.panelEnum.equals(PanelEnum.CHAIN_MANAGER)) {
            ((ChainManagerPanel) panel).getStoreOrders(storeId);
            ((ChainManagerPanel) panel).getStoreCatalog(storeId);
            ((ChainManagerPanel) panel).getAllOrders();
        } else if (DashBoardController.panelEnum.equals(PanelEnum.STORE_MANAGER)) {
            ((StoreManagerPanel) panel).getStoreOrders(1);
            ((StoreManagerPanel) panel).getStoreCatalog(1);
            storeList.setVisible(false);
            chooseStoreLabel.setVisible(false);
            newScreenBtn.setVisible(false);
            totalChainNumberOfOrders.setVisible(false);
        }
    }
}
