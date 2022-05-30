package org.lilachshop.employeeclient;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.lilachshop.entities.Store;

public class CatalogEditPopUpController {

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
    private ChoiceBox<Store> storeChoiceBox;

    @FXML
    void onClickSaveBtn(ActionEvent event) {

    }

    @FXML
    void uploadImg(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert DescriptionTF != null : "fx:id=\"DescriptionTF\" was not injected: check your FXML file 'CatalogEditPopUp.fxml'.";
        assert itemIDTF != null : "fx:id=\"itemIDTF\" was not injected: check your FXML file 'CatalogEditPopUp.fxml'.";
        assert itemImgUpload != null : "fx:id=\"itemImgUpload\" was not injected: check your FXML file 'CatalogEditPopUp.fxml'.";
        assert itemNameTF != null : "fx:id=\"itemNameTF\" was not injected: check your FXML file 'CatalogEditPopUp.fxml'.";
        assert priceTF != null : "fx:id=\"priceTF\" was not injected: check your FXML file 'CatalogEditPopUp.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'CatalogEditPopUp.fxml'.";
        assert storeChoiceBox != null : "fx:id=\"storeChoiceBox\" was not injected: check your FXML file 'CatalogEditPopUp.fxml'.";

    }
}
