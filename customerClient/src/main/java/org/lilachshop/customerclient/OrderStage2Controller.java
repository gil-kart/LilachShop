/**
 * Sample Skeleton for 'OrderStage2.fxml' Controller Class
 */

package org.lilachshop.customerclient;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.lilachshop.entities.DeliveryDetails;
import org.lilachshop.entities.Order;
import org.lilachshop.entities.PickUpDetails;

public class OrderStage2Controller {
    Order myOrder;
    boolean deliveryBool = false;
    boolean pickupBool = false;
    boolean chooseBoxPickUp = false;
    boolean chooseBoxDelivery = false;

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
    private ComboBox<Integer> selfTime; // Value injected by FXMLLoader

    @FXML // fx:id="shopNum"
    private Text shopNum; // Value injected by FXMLLoader

    @FXML // fx:id="time"
    private ComboBox<Integer> time; // Value injected by FXMLLoader

    @FXML
    private HBox selfHboxDate;

    @FXML
    private HBox selfHboxTime;


    @FXML
    private HBox deliveryHboxDate;

    @FXML
    private HBox deliveryHboxTime;

    @FXML
    void gotoNext(ActionEvent event) {
        boolean flagNext = false;
        if (deliveryBool)
        {
            if (!receipient.getText().equals("") && !phoneNum.getText().equals("") && !address.getText().equals("")) {
                if (receiveImm.isSelected()) {
                    LocalDateTime dateDelivery = LocalDateTime.now().with(LocalTime.now());
                    DeliveryDetails delivery = new DeliveryDetails(dateDelivery, phoneNum.getText(), receipient.getText(), address.getText());
                    myOrder.setDeliveryDetails(delivery);
                    flagNext = true;
                } else {
                    if (selfDate.getValue() != null && time.getValue() != null) {
                        LocalDateTime dateDelivery = LocalDateTime.of(date.getValue().getYear(), date.getValue().getMonth(), date.getValue().getDayOfMonth(), time.getSelectionModel().getSelectedItem(), 0, 0);
                        DeliveryDetails delivery = new DeliveryDetails(dateDelivery, phoneNum.getText(), receipient.getText(), address.getText());
                        myOrder.setDeliveryDetails(delivery);
                        flagNext = true;
                    }
                }
            }

        }
        else
        {
            if(selfRecieveImm.isSelected())
            {

                flagNext = true;
            }
            else
            {
                if (selfDate.getValue() != null && selfTime != null) {
                    LocalDateTime datePickUp = LocalDateTime.of(selfDate.getValue().getYear(), selfDate.getValue().getMonth(), selfDate.getValue().getDayOfMonth(), selfTime.getValue(),0,0);
                    PickUpDetails pickUp = new PickUpDetails(datePickUp);
                    myOrder.setPickUpDetails(pickUp);
                    flagNext = true;
                }
            }

        }

        if(flagNext)
        {
            Stage stage = CustomerApp.getStage();
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
        else
        {
            Alert a = new Alert(Alert.AlertType.NONE);
            ButtonType button = new ButtonType("אישור");
            a.getButtonTypes().setAll(button);
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setHeaderText("נא מלא את כל הפרטים באופן תקין");
            a.setTitle("מילוי פרטים באופן חלקי");
            a.setContentText("");
            a.show();
        }
    }

    @FXML
    void gotoPrev(ActionEvent event) {
        Stage stage = CustomerApp.getStage();
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
    void returnToCatalog(MouseEvent event) {
        CustomerApp.getCustomerCatalog();
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
        selfTime.getItems().addAll(8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        time.getItems().addAll(8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        receipient.setText(CustomerApp.getMyCustomer().getName());
        phoneNum.setText(CustomerApp.getMyCustomer().getPhoneNumber());
        address.setText(CustomerApp.getMyCustomer().getAddress());
        name.setText("שלום, " + CustomerApp.getMyCustomer().getName());
        shopNum.setText(CustomerApp.getMyStore().getStoreName());
    }

    @FXML
    void onSelfRecieveImm(ActionEvent event) {
            selfTime.getSelectionModel().clearSelection();
            selfHboxDate.setVisible(chooseBoxPickUp);
            selfDate.getEditor().clear();
            selfHboxTime.setVisible(chooseBoxPickUp);
            chooseBoxPickUp = !chooseBoxPickUp;
    }

    @FXML
    void onRecieveImm(ActionEvent event) {
        time.getSelectionModel().clearSelection();
        deliveryHboxDate.setVisible(chooseBoxDelivery);
        date.getEditor().clear();
        deliveryHboxTime.setVisible(chooseBoxDelivery);
        chooseBoxDelivery = !chooseBoxDelivery;
    }

    public void showInfo(Order order) {
        myOrder = order;
    }
}
