/**
 * Sample Skeleton for 'OrderStage2.fxml' Controller Class
 */

package org.lilachshop.customerclient;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.lilachshop.entities.Order;
import org.lilachshop.entities.PickUpDetails;
import org.lilachshop.panels.Panel;

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
    private ComboBox<String> selfTime; // Value injected by FXMLLoader

    @FXML // fx:id="shopNum"
    private Text shopNum; // Value injected by FXMLLoader

    @FXML // fx:id="time"
    private ComboBox<String> time; // Value injected by FXMLLoader

    @FXML
    void gotoNext(ActionEvent event) {
        boolean flagNext = true;

        if(flagNext)
        {
            Stage stage = App.getStage();
            try {
                FXMLLoader fxmlLoader1 = new FXMLLoader(CartController.class.getResource("OrderStage3.fxml"));
                Parent root = fxmlLoader1.load();
                OrderStage3Controller orderStage3Controller = fxmlLoader1.getController();
                if(deliveryBool){
                }
                else {
                    LocalDate datePickUp = LocalDate.of (selfDate.getValue().getYear(),selfDate.getValue().getMonth(),selfDate.getValue().getDayOfMonth());
                    PickUpDetails pickUp = new PickUpDetails(datePickUp);
                    myOrder.setPickUpDetails(pickUp);
                }
                orderStage3Controller.showInfo(myOrder);
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
        App.getCustomerCatalog();
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
        selfTime.getItems().addAll("8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00","17:00","18:00","19:00","20:00");
        time.getItems().addAll("8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00","17:00","18:00","19:00","20:00");
        receipient.setText(App.getMyCustomer().getName());
        phoneNum.setText(App.getMyCustomer().getPhoneNumber());
        address.setText(App.getMyCustomer().getAddress());
        name.setText("שלום, " + App.getMyCustomer().getName());
    }

    public void showInfo(Order order) {
        myOrder = order;
    }
}
