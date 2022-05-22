package org.lilachshop.employeeclient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class ComplaintWorkerResponseController {
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

    CustomerServiceViewController controllerInstance;

    public void setComplaintData(String _complaintNumber, String _complaintStatus, String _lastDateToHandle,
                                 String _content, CustomerServiceViewController _controllerInstance){
        complaintNumber.setText(_complaintNumber);
        complaintStatus.setText(_complaintStatus);
        lastDateToHandle.setText(_lastDateToHandle);
        content.setText(_content);
        controllerInstance = _controllerInstance;
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
