/**
 * Sample Skeleton for 'OrderStage3.fxml' Controller Class
 */

package org.lilachshop.customerclient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OrderStage3Controller {
    Order order;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="amount"
    private Text amount; // Value injected by FXMLLoader

    @FXML // fx:id="itemLayout"
    private VBox itemLayout; // Value injected by FXMLLoader

    @FXML // fx:id="name"
    private Label name; // Value injected by FXMLLoader

    @FXML // fx:id="next"
    private Button next; // Value injected by FXMLLoader

    @FXML // fx:id="prev"
    private Button prev; // Value injected by FXMLLoader

    @FXML // fx:id="shipPayment"
    private Text shipPayment; // Value injected by FXMLLoader

    @FXML // fx:id="totalPrice"
    private Text totalPrice; // Value injected by FXMLLoader

    @FXML
    void gotoNext(ActionEvent event) {
        Stage stage = App.getStage();
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(CartController.class.getResource("OrderStage4.fxml"));
            Parent root = fxmlLoader1.load();
            OrderStage4Controller orderStage4Controller = fxmlLoader1.getController();
            orderStage4Controller.showInfo(order);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void gotoPrev(ActionEvent event) {
        Stage stage = App.getStage();
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(CartController.class.getResource("OrderStage2.fxml"));
            Parent root = fxmlLoader1.load();
            OrderStage2Controller orderStage2Controller = fxmlLoader1.getController();
            orderStage2Controller.showInfo(order);
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
            catalogController.showInfo(order.getMyOrder());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert amount != null : "fx:id=\"amount\" was not injected: check your FXML file 'OrderStage3.fxml'.";
        assert itemLayout != null : "fx:id=\"itemLayout\" was not injected: check your FXML file 'OrderStage3.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'OrderStage3.fxml'.";
        assert next != null : "fx:id=\"next\" was not injected: check your FXML file 'OrderStage3.fxml'.";
        assert prev != null : "fx:id=\"prev\" was not injected: check your FXML file 'OrderStage3.fxml'.";
        assert shipPayment != null : "fx:id=\"shipPayment\" was not injected: check your FXML file 'OrderStage3.fxml'.";
        assert totalPrice != null : "fx:id=\"totalPrice\" was not injected: check your FXML file 'OrderStage3.fxml'.";

    }


    public void showInfo(Order myOrder) {
        order = myOrder;
    }
}
