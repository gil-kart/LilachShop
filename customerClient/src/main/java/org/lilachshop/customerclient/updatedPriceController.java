/**
 * Sample Skeleton for 'updatePrice.fxml' Controller Class
 */

package org.lilachshop.customerclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class updatedPriceController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="newPriceLabel"
    private Label newPriceLabel; // Value injected by FXMLLoader

    @FXML // fx:id="origPrice"
    private Label origPrice; // Value injected by FXMLLoader

    @FXML // fx:id="priceLabel"
    private Label priceLabel; // Value injected by FXMLLoader

    @FXML // fx:id="price_text"
    private TextField price_text; // Value injected by FXMLLoader

    @FXML // fx:id="update_button"
    private Button update_button; // Value injected by FXMLLoader

    @FXML
    void onUpdate(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert newPriceLabel != null : "fx:id=\"newPriceLabel\" was not injected: check your FXML file 'updatePrice.fxml'.";
        assert origPrice != null : "fx:id=\"origPrice\" was not injected: check your FXML file 'updatePrice.fxml'.";
        assert priceLabel != null : "fx:id=\"priceLabel\" was not injected: check your FXML file 'updatePrice.fxml'.";
        assert price_text != null : "fx:id=\"price_text\" was not injected: check your FXML file 'updatePrice.fxml'.";
        assert update_button != null : "fx:id=\"update_button\" was not injected: check your FXML file 'updatePrice.fxml'.";

    }

}
