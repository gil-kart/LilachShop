package org.lilachshop.employeeclient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.lilachshop.entities.Order;

import java.io.IOException;
import java.time.LocalDate;

public class ComplaintWorkerResponseController {
    CustomerServiceViewController controllerInstance;
    Order order;
    @FXML
    private Label complaintNumber;

    @FXML
    private Label complaintStatus;

    @FXML
    private Label lastDateToHandle;

    @FXML
    private TextArea response;

    @FXML
    private TextArea content;

    @FXML
    private Button SendBtn;

    @FXML
    private Button showItems;

    @FXML
    private Label orderNum;

    @FXML
    private Label orderPrice;

    @FXML
    private Label customerName;

    @FXML
    void onShowItemsClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShowOrder.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        ShowOrderController controller = fxmlLoader.getController();
        controller.setData(order);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void setComplaintData(String _complaintNumber, String _complaintStatus, LocalDate _lastDateToHandle,
                                 String _content, CustomerServiceViewController _controllerInstance,Order order){
        complaintNumber.setText(_complaintNumber);
        complaintStatus.setText(_complaintStatus);
        lastDateToHandle.setText(_lastDateToHandle.toString());
        content.setText(_content);
        this.order = order;
        controllerInstance = _controllerInstance;
        this.orderNum.setText(String.valueOf(order.getId()));
        this.orderPrice.setText(String.valueOf(order.getTotalPrice() + "ש''ח"));
        this.customerName.setText(String.valueOf(order.getCustomer().getName()));
    }
    @FXML private Button closeButton;

    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    @FXML
    void onSendBtn(ActionEvent event) throws IOException {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setHeaderText("תגובתך לתלונה נשלחה ללקוח, התלונה נסגרה");
        a.setTitle("תגובה לתלונת לקוח");
        a.setContentText("");
        a.show();
        controllerInstance.closeComplaint(complaintNumber.getText(), response.getText());
        Stage stage = (Stage) SendBtn.getScene().getWindow();
        stage.close();
    }
}
