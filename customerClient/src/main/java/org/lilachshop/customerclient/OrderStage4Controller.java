/**
 * Sample Skeleton for 'OrderStage4.fxml' Controller Class
 */

package org.lilachshop.customerclient;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
import org.lilachshop.entities.PickUpDetails;
import org.lilachshop.entities.myOrderItem;
import org.lilachshop.panels.Panel;
import org.lilachshop.panels.StoreCustomerPanel;

public class OrderStage4Controller {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cardNum"
    private TextField cv;

    @FXML // fx:id="cardNum"
    private TextField cardNum; // Value injected by FXMLLoader

    @FXML // fx:id="idCard"
    private TextField idCard; // Value injected by FXMLLoader

    @FXML // fx:id="name"
    private Label name; // Value injected by FXMLLoader

    @FXML // fx:id="next"
    private Button next; // Value injected by FXMLLoader

    @FXML // fx:id="ownerName"
    private TextField ownerName; // Value injected by FXMLLoader

    @FXML // fx:id="prev"
    private Button prev; // Value injected by FXMLLoader

    @FXML
    private TextField validMonth;

    @FXML
    private TextField validYear;

    Order myOrder;

    @FXML
    void endOrder(ActionEvent event) {
        LocalDate cardValid = LocalDate.of (Integer.parseInt(validYear.getText()) , Integer.parseInt(validMonth.getText()),1);
        //CreditCard creditCard = new CreditCard(cardNum.getText(),cardValid,name.getText(),idCard.getText(),cv.getText());
        ((StoreCustomerPanel)App.getPanel()).sendNewOrderCreationToServer(myOrder);

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

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cardNum != null : "fx:id=\"cardNum\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert idCard != null : "fx:id=\"idCard\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert next != null : "fx:id=\"next\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert ownerName != null : "fx:id=\"ownerName\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert prev != null : "fx:id=\"prev\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        name.setText("שלום, " + App.getMyCustomer().getName());
        cardNum.setText(App.getMyCustomer().getCard().getNumber());
        validMonth.setText(Integer.toString(App.getMyCustomer().getCard().getExpDate().getMonth().getValue()));
        validYear.setText(Integer.toString(App.getMyCustomer().getCard().getExpDate().getYear()));
        name.setText(App.getMyCustomer().getName());
        idCard.setText(Long.toString(App.getMyCustomer().getId()));
        cv.setText(App.getMyCustomer().getCard().getThreeDigits());

    }

    public void showInfo(Order order) {
        myOrder = order;
    }
}
