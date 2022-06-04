/**
 * Sample Skeleton for 'OrderStage4.fxml' Controller Class
 */

package org.lilachshop.customerclient;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.lilachshop.entities.CreditCard;
import org.lilachshop.entities.Order;
import org.lilachshop.entities.myOrderItem;

public class OrderStage4Controller {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private TextField cardNumLabel;

    @FXML
    private TextField creditNumTF;

    @FXML
    private TextField cvcTF;

    @FXML
    private TextField expDateTF;

    @FXML
    private TextField idCardLabel;

    @FXML
    private TextField idCardTF;

    @FXML
    private Label name;

    @FXML
    private Button next;

    @FXML
    private TextField ownerNameLabel;

    @FXML
    private TextField ownerNameTF;

    @FXML
    private Button prev;

    Order myOrder;

    @FXML
    void endOrder(ActionEvent event) {

        CreditCard card = validateCreditCard();
        if (card != null) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setHeaderText("אישור הזמנה");
            a.setTitle("אישור הזמנה");
            a.setContentText("ההזמנתך אושרה בהצלחה");
            a.initModality(Modality.APPLICATION_MODAL);
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK) {
                App.setMyFlowers(new LinkedList<myOrderItem>());
                App.getCustomerCatalog();
            }
        }
    }

    @FXML
    void gotoPrev(ActionEvent event) {
        Stage stage = App.getStage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CartController.class.getResource("OrderStage3.fxml"));
            Parent root = fxmlLoader.load();
            OrderStage3Controller orderStage3Controller = fxmlLoader.getController();
            orderStage3Controller.showInfo(myOrder);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gotoSignUpOrPersonalArea(MouseEvent event) {

    }

    @FXML
    void returnToCatalog(MouseEvent event) {
        App.getCustomerCatalog();

    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cardNumLabel != null : "fx:id=\"cardNumLabel\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert creditNumTF != null : "fx:id=\"creditNumTF\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert cvcTF != null : "fx:id=\"cvcTF\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert expDateTF != null : "fx:id=\"expDateTF\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert idCardLabel != null : "fx:id=\"idCardLabel\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert idCardTF != null : "fx:id=\"idCardTF\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert next != null : "fx:id=\"next\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert ownerNameLabel != null : "fx:id=\"ownerNameLabel\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert ownerNameTF != null : "fx:id=\"ownerNameTF\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert prev != null : "fx:id=\"prev\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        name.setText("שלום, " + App.getMyCustomer().getName());
        creditNumTF.setText(App.getMyCustomer().getCard().getNumber().toString());
        expDateTF.setText(App.getMyCustomer().getCard().getExpDateStringFormat());
        ownerNameTF.setText(App.getMyCustomer().getCard().getOwnerName());
        idCardTF.setText(Long.toString(App.getMyCustomer().getId()));
        cvcTF.setText(App.getMyCustomer().getCard().getThreeDigits());
    }

    private CreditCard validateCreditCard() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        CreditCard card = new CreditCard();

        try {
            card.setNumber(creditNumTF.getText());
            card.setOwnerName(ownerNameTF.getText());
            card.setExpDate(expDateTF.getText());
            card.setThreeDigits(cvcTF.getText());
            card.setCardOwnerId(idCardTF.getText());
        } catch (Exception e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
            return null;
        }
        return card;
    }

    public void showInfo(Order order) {
        myOrder = order;
    }
}
