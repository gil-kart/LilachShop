/**
 * Sample Skeleton for 'OrderStage4.fxml' Controller Class
 */

package org.lilachshop.customerclient;

import java.io.IOException;
import java.net.URL;
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

public class OrderStage4Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

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

    @FXML // fx:id="valid"
    private TextField valid; // Value injected by FXMLLoader

    Order myOrder;

    @FXML
    void endOrder(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setHeaderText("אישור הזמנה");
        a.setTitle("אישור הזמנה");
        a.setContentText("ההזמנתך אושרה בהצלחה");
        a.initModality(Modality.APPLICATION_MODAL);
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = App.getStage();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(CartController.class.getResource("main.fxml"));
                Parent root = fxmlLoader.load();
                CatalogController catalogController = fxmlLoader.getController();
                catalogController.showInfo(myOrder.getMyOrder());
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
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
        Stage stage = App.getStage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CartController.class.getResource("main.fxml"));
            Parent root = fxmlLoader.load();
            CatalogController catalogController = fxmlLoader.getController();
            catalogController.showInfo(myOrder.getMyOrder());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cardNum != null : "fx:id=\"cardNum\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert idCard != null : "fx:id=\"idCard\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert next != null : "fx:id=\"next\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert ownerName != null : "fx:id=\"ownerName\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert prev != null : "fx:id=\"prev\" was not injected: check your FXML file 'OrderStage4.fxml'.";
        assert valid != null : "fx:id=\"valid\" was not injected: check your FXML file 'OrderStage4.fxml'.";

    }

    public void showInfo(Order order) {
        myOrder = order;
    }
}
