package org.lilachshop.employeeclient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.lilachshop.entities.Item;
import org.lilachshop.entities.Store;

public class CatalogEditTableController {

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<String, String> description;

    @FXML
    private TableView<Item> itemTable;

    @FXML
    private TableColumn<Integer, Integer> price;

    @FXML
    private TableColumn<Long, Long> productID;

    @FXML
    private TableColumn<String, String> productName;

    @FXML
    private TableColumn<Store, String> store;

    @FXML
    private ChoiceBox<Store> storeCatalogChoice;

    @FXML
    private Button updateBtn;

}
