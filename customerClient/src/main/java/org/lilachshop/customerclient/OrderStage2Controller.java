/**
 * Sample Skeleton for 'OrderStage2.fxml' Controller Class
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OrderStage2Controller {
    Order myOrder;
    boolean deliveryBool = false;
    boolean pickupBool = false;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="address"
    private TextField address; // Value injected by FXMLLoader

    @FXML // fx:id="costumerRec"
    private CheckBox costumerRec; // Value injected by FXMLLoader

    @FXML // fx:id="date"
    private DatePicker date; // Value injected by FXMLLoader

    @FXML // fx:id="delivery"
    private VBox delivery; // Value injected by FXMLLoader

    @FXML // fx:id="deliveryCheck"
    private CheckBox deliveryCheck; // Value injected by FXMLLoader

    @FXML // fx:id="name"
    private Label name; // Value injected by FXMLLoader

    @FXML // fx:id="next"
    private Button next; // Value injected by FXMLLoader

    @FXML // fx:id="phoneNum"
    private TextField phoneNum; // Value injected by FXMLLoader

    @FXML // fx:id="pickup"
    private VBox pickup; // Value injected by FXMLLoader

    @FXML // fx:id="pickupCheck"
    private CheckBox pickupCheck; // Value injected by FXMLLoader

    @FXML // fx:id="prev"
    private Button prev; // Value injected by FXMLLoader

    @FXML // fx:id="receipient"
    private TextField receipient; // Value injected by FXMLLoader

    @FXML // fx:id="receiveImm"
    private CheckBox receiveImm; // Value injected by FXMLLoader

    @FXML // fx:id="selfDate"
    private DatePicker selfDate; // Value injected by FXMLLoader

    @FXML // fx:id="selfRecieveImm"
    private CheckBox selfRecieveImm; // Value injected by FXMLLoader

    @FXML // fx:id="selfTime"
    private ComboBox<?> selfTime; // Value injected by FXMLLoader

    @FXML // fx:id="shopNum"
    private Text shopNum; // Value injected by FXMLLoader

    @FXML // fx:id="time"
    private ComboBox<?> time; // Value injected by FXMLLoader

    @FXML
    void gotoNext(ActionEvent event) {
        Stage stage = App.getStage();
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(CartController.class.getResource("OrderStage3.fxml"));
            Parent root = fxmlLoader1.load();
            OrderStage3Controller orderStage3Controller = fxmlLoader1.getController();
            orderStage3Controller.showInfo(myOrder);
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
            FXMLLoader fxmlLoader1 = new FXMLLoader(CartController.class.getResource("OrderStage1.fxml"));
            Parent root = fxmlLoader1.load();
            OrderStage1Controller orderStage1Controller = fxmlLoader1.getController();
            orderStage1Controller.showInfo(myOrder);
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

    @FXML
    void showDelivery(ActionEvent event) {
        if(!deliveryBool)
        {
            deliveryBool = true;
            delivery.setVisible(true);
            if (pickupBool) {
                pickupCheck.setSelected(false);
                pickup.setVisible(false);
                pickupBool = false;
            }
        }
        else
        {
            delivery.setVisible(false);
            deliveryBool = false;
        }
    }

    @FXML
    void showPickUp(ActionEvent event) {
        if(!pickupBool)
        {
            pickupBool = true;
            pickup.setVisible(true);
            if (deliveryBool) {
                deliveryCheck.setSelected(false);
                delivery.setVisible(false);
                deliveryBool = false;
            }
        }
        else
        {
            pickup.setVisible(false);
            pickupBool = false;
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

    public void showInfo(Order order) {
        myOrder = order;
    }
}
