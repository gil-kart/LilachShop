package org.lilachshop.employeeclient;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.employeeclient.employeeEvents.AddItemEvent;
import org.lilachshop.entities.*;
import org.lilachshop.events.RefreshCatalogEvent;
import org.lilachshop.panels.GeneralEmployeePanel;
import org.lilachshop.panels.OperationsPanelFactory;
import org.lilachshop.panels.Panel;
import org.lilachshop.panels.PanelEnum;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import static antlr.build.ANTLR.root;


public class CatalogEditTableController implements Initializable {

    static public Set<Catalog> allCatalog = null;
    static public Panel panel = null;

    private static Stage stage = null;
    private static Parent root = null;
    private static FXMLLoader fxmlLoader = null;
    private static Scene scene = null;

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<Item> itemTable;

    @FXML
    private TableColumn<Item, Integer> discount;

    @FXML
    private TableColumn<Item, Long> id;

    @FXML
    private TableColumn<Item, String> name;

    @FXML
    private TableColumn<Item, Integer> price;


    @FXML
    private TableColumn<Item, Color> itemColorCol;


    @FXML
    private TableColumn<Item, String> descriptionCol;

    @FXML
    private TableColumn<Item, ItemType> itemTypeCol;

    @FXML
    private ChoiceBox<Catalog> storeCatalogChoice;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //setting connection to receive msg from Pop-up
        EventBus.getDefault().register(this);

        //Setting up the table values
        Platform.runLater(() -> {
            this.name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
            this.price.setCellValueFactory(new PropertyValueFactory<Item, Integer>("Price"));
            this.id.setCellValueFactory(new PropertyValueFactory<Item, Long>("id"));
            this.discount.setCellValueFactory(new PropertyValueFactory<Item, Integer>("percent"));
            this.descriptionCol.setCellValueFactory(new PropertyValueFactory<Item, String>("description"));
            this.itemColorCol.setCellValueFactory((new PropertyValueFactory<Item, Color>("color")));
            this.itemTypeCol.setCellValueFactory((new PropertyValueFactory<Item, ItemType>("itemType")));
        });


        //Setting up panel
        panel = OperationsPanelFactory.createPanel(PanelEnum.GENERAL_EMPLOYEE, this);
        GeneralEmployeePanel generalEmployeePanel = (GeneralEmployeePanel) panel;

        //Setting up listener to CatalogChoiceBox
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if ((newValue != null)) {
                    Catalog catalogToTable = (Catalog) newValue;
                    System.out.println(catalogToTable.getId());
                    System.out.println("send request to server to get the catalog" + catalogToTable.getId());
                    generalEmployeePanel.getCatalogByID(catalogToTable.getId());
                }
            }
        };
        storeCatalogChoice.getSelectionModel().selectedItemProperty().addListener(changeListener);


        //Setting up the choices in ChoiceBox
        if (allCatalog == null) {
            generalEmployeePanel.getAllCatalog();
        } else {
            storeCatalogChoice.setItems(FXCollections.observableArrayList(allCatalog));
        }

    }


    @Subscribe
    public void onRefreshTable(RefreshCatalogEvent event) {
        System.out.println("Refresh table with catalog " + event.getId());
        Platform.runLater(() -> {
            storeCatalogChoice.getSelectionModel().clearSelection();
            storeCatalogChoice.getSelectionModel().select(event.getId() - 1);
        });


    }

    //When server sends list of items Display in Table
    @Subscribe
    public void onGetCatalog(Catalog catalog) {
        System.out.println("This is Catalog from server" + catalog.getId());
        Platform.runLater(() -> {
            ObservableList<Item> storeCatalog = FXCollections.observableArrayList(catalog.getItems());
            itemTable.getItems().clear();
            itemTable.setItems(storeCatalog);
            itemTable.refresh();
        });
    }

    @Subscribe
    public void onGetAllCatalogs(List<Catalog> allCatalogs) {
        Platform.runLater(() -> {
            System.out.println("Setting up Catalog Choice");
            System.out.println(allCatalogs);
            storeCatalogChoice.setItems(FXCollections.observableArrayList(allCatalogs));
            storeCatalogChoice.getSelectionModel().select(0);
        });
    }


    @FXML
    void onClickAddbtn(ActionEvent event) throws IOException {
        stage = stage == null ? new Stage() : stage;
        fxmlLoader = fxmlLoader == null ? new FXMLLoader(App.class.getResource("CatalogEditPopUp.fxml")) : fxmlLoader;
        if (root == null) {
            try {
                root = fxmlLoader.load();
            } catch (Exception e) {
            }
        }
        //sending relevant info to pop up
        EventBus.getDefault().post(new AddItemEvent(((GeneralEmployeePanel) panel).getEmployee()));

        scene = scene == null ? new Scene(root) : scene;
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void onClickDeleteBtn(ActionEvent event) {

    }
}
