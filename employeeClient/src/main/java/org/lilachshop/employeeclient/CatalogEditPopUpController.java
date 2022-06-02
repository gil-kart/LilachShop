package org.lilachshop.employeeclient;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.employeeclient.employeeEvents.AddItemEvent;
import org.lilachshop.entities.*;
import org.lilachshop.events.RefreshCatalogEvent;
import org.lilachshop.panels.GeneralEmployeePanel;
import org.lilachshop.panels.OperationsPanelFactory;
import org.lilachshop.panels.Panel;
import org.lilachshop.panels.PanelEnum;
import org.lilachshop.requests.CatalogRequest;

public class CatalogEditPopUpController implements Initializable {

    static public Panel panel = null;
    private boolean saveMode;
    private boolean editMode;
    static public Set<Catalog> allCatalog = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea DescriptionTF;

    @FXML
    private TextField itemIDTF;

    @FXML
    private ImageView itemImgUpload;

    @FXML
    private TextField itemNameTF;

    @FXML
    private TextField priceTF;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField discountTF;

    public void setStoreChoiceBox(ChoiceBox<Catalog> storeChoiceBox) {
        this.catalogChoiceBox = storeChoiceBox;
    }

    @FXML
    private ChoiceBox<Catalog> catalogChoiceBox;

    @FXML
    private ChoiceBox<ItemType> itemTypeChoiceBox;

    @FXML
    private ChoiceBox<Color> itemColorChoiceBox;


    Alert a = new Alert(Alert.AlertType.INFORMATION);


    @FXML
    void onClickSaveBtn(ActionEvent event) {

        //Save attributes of the new Item
        Item item = new Item(itemNameTF.getText(), DescriptionTF.getText(),
                Integer.parseInt(discountTF.getText()),
                Integer.parseInt(priceTF.getText())
                , itemTypeChoiceBox.getSelectionModel().getSelectedItem(),
                itemColorChoiceBox.getSelectionModel().getSelectedItem(),
                null);

        GeneralEmployeePanel generalEmployeePanel = (GeneralEmployeePanel) panel;
        generalEmployeePanel.saveNewItem(item, catalogChoiceBox.getSelectionModel().getSelectedItem());

    }

    @Subscribe
    public void onChangesToDB(String msg) {
        System.out.println(msg);
        Catalog catalog = catalogChoiceBox.getSelectionModel().getSelectedItem();
        EventBus.getDefault().post(new RefreshCatalogEvent(catalog.getId()));
        Platform.runLater(() -> {
            clearAllTextField();
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("Item added");
            a.show();
        });
    }


    @FXML
    void uploadImg(MouseEvent event) {

    }

    @Subscribe
    public void onAddItemEvent(AddItemEvent event) {
        System.out.println("POP UP - ADD ITEM");
        saveMode = true;
        editMode = false;

        if (panel == null) {
            panel = OperationsPanelFactory.createPanel(PanelEnum.GENERAL_EMPLOYEE, this);
            GeneralEmployeePanel generalEmployeePanel = (GeneralEmployeePanel) panel;

            if (allCatalog == null) {
                generalEmployeePanel.getAllCatalog();
            } else {
                Platform.runLater(() -> {
                    catalogChoiceBox.setItems(FXCollections.observableArrayList(allCatalog));
                });
            }
        }
    }

    @Subscribe
    public void onGetAllCatalog(List<Catalog> catalogs) {
        allCatalog = allCatalog == null ? new HashSet<>(catalogs) : allCatalog;
        Platform.runLater(() -> {
            catalogChoiceBox.setItems(FXCollections.observableArrayList(catalogs));
            DescriptionTF.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::onCloseWindowEvent);
        });
    }


    @FXML
    void initialize() {
        assert DescriptionTF != null : "fx:id=\"DescriptionTF\" was not injected: check your FXML file 'CatalogEditPopUp.fxml'.";
        assert itemIDTF != null : "fx:id=\"itemIDTF\" was not injected: check your FXML file 'CatalogEditPopUp.fxml'.";
        assert itemImgUpload != null : "fx:id=\"itemImgUpload\" was not injected: check your FXML file 'CatalogEditPopUp.fxml'.";
        assert itemNameTF != null : "fx:id=\"itemNameTF\" was not injected: check your FXML file 'CatalogEditPopUp.fxml'.";
        assert priceTF != null : "fx:id=\"priceTF\" was not injected: check your FXML file 'CatalogEditPopUp.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'CatalogEditPopUp.fxml'.";
        assert catalogChoiceBox != null : "fx:id=\"storeChoiceBox\" was not injected: check your FXML file 'CatalogEditPopUp.fxml'.";

    }

    public void onCloseWindowEvent(WindowEvent event) {
        // clear this window
        clearAllTextField();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setting up EventBus
        if (!(EventBus.getDefault().isRegistered(this))) {
            EventBus.getDefault().register(this);
        }

        Platform.runLater(() -> {
            //Setting up Enum ChoiceBox
            itemTypeChoiceBox.setItems(FXCollections.observableArrayList(ItemType.values()));
            itemColorChoiceBox.setItems((FXCollections.observableArrayList(Color.values())));
        });

    }


    public void clearAllTextField() {
        itemNameTF.clear();
        priceTF.clear();
        discountTF.clear();
        DescriptionTF.clear();
        itemColorChoiceBox.setValue(null);
        itemTypeChoiceBox.setValue(null);
        catalogChoiceBox.getSelectionModel().clearSelection();
    }
}
