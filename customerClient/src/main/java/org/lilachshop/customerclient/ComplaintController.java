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
import java.util.ResourceBundle;

import org.lilachshop.panels.OperationsPanelFactory;
import org.lilachshop.panels.ExamplePanel;
import org.lilachshop.panels.Panel;
import org.greenrobot.eventbus.Subscribe;

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

        ((ExamplePanel) panel).sendMessageToServer("example message");

        Complaint complaint = new Complaint("creationDate", "open", "1234", complaintText.getText());
        System.out.println(complaint.getContent());
        // this is the part where we send the data to the server
        try {
            App.setRoot("main");
        } catch (Exception e) {
            System.out.println("cant go to main screen");
            e.printStackTrace();
            Stage stage = (Stage) onSendComplaintClick.getScene().getWindow();
            stage.close();
        }

    }

    @Subscribe
    public void handleMessageReceivedFromClient(String msg) {
        System.out.println("hi");
    }

    @FXML
    void initialize() {
        assert complaintText != null : "fx:id=\"complaintText\" was not injected: check your FXML file 'complaintForm.fxml'.";
        assert onSendComplaintClick != null : "fx:id=\"onSendComplaintClick\" was not injected: check your FXML file 'complaintForm.fxml'.";
        assert orderNumber != null : "fx:id=\"orderNumber\" was not injected: check your FXML file 'complaintForm.fxml'.";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        panel = OperationsPanelFactory.createPanel(1, this); // this should be the default panel according to customer/employee
        if (panel == null) {
            throw new RuntimeException("Panel creation failed!");
        }
    }
}
