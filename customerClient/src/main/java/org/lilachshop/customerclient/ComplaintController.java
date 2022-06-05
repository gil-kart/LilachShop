package org.lilachshop.customerclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.lilachshop.entities.Complaint;
import org.lilachshop.entities.ComplaintStatus;
import org.lilachshop.panels.OperationsPanelFactory;
import org.lilachshop.panels.Panel;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.panels.PanelEnum;
import org.lilachshop.panels.StoreCustomerPanel;

public class ComplaintController implements Initializable {

    private static Panel panel;

    @FXML
    private Button onSendComplaintClick;

    @FXML
    private TextArea complaintText;

    @FXML
    private Label orderNumber;

    @FXML
    void SendComplaintClicked(ActionEvent event) throws IOException {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setHeaderText("תלונתך נשלחה למערכת");
        a.setTitle("הגשת תלונה");
        a.setContentText("");
        a.show();


        Complaint complaint = new Complaint(LocalDate.of(2022, 5, 28), ComplaintStatus.OPEN, complaintText.getText(), LocalDate.of(2022, 5, 27), "");

        ((StoreCustomerPanel) panel).sendComplaintToServer(complaint);
//        ((StoreCustomerPanel) panel).sendGetGeneralCatalogRequestToServer();
        System.out.println(complaint.getContent());
//        Stage stage = (Stage) onSendComplaintClick.getScene().getWindow();
//        stage.close();
//        try {
//            App.setRoot("main");
//        } catch (Exception e) {
//            System.out.println("cant go to main screen");
//            e.printStackTrace();
//            Stage stage = (Stage) onSendComplaintClick.getScene().getWindow();
//            stage.close();
//        }

    }

    @Subscribe
    public void handleMessageReceivedFromClient(String msg) {
        System.out.println("complaintController recieved message from server");
    }

    @FXML
    void initialize() {
        assert complaintText != null : "fx:id=\"complaintText\" was not injected: check your FXML file 'complaintForm.fxml'.";
        assert onSendComplaintClick != null : "fx:id=\"onSendComplaintClick\" was not injected: check your FXML file 'complaintForm.fxml'.";
        assert orderNumber != null : "fx:id=\"orderNumber\" was not injected: check your FXML file 'complaintForm.fxml'.";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        panel = OperationsPanelFactory.createPanel(PanelEnum.STORE_CUSTOMER, CustomerApp.getSocket(), this); // this should be the default panel according to customer/employee
        if (panel == null) {
            throw new RuntimeException("Panel creation failed!");
        }
    }
}
