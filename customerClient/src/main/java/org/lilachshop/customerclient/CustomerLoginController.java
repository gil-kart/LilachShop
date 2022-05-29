package org.lilachshop.customerclient;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.entities.Complaint;
import org.lilachshop.entities.Customer;
import org.lilachshop.panels.CustomerAnonymousPanel;
import org.lilachshop.panels.OperationsPanelFactory;
import org.lilachshop.panels.Panel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerLoginController implements Initializable {
    private static Panel panel;

    @FXML
    private Button CreateAccountBtn;

    @FXML
    private Button LoginBtn;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField UserName;

    @FXML
    void onCreateAccountBtnClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        panel = OperationsPanelFactory.createPanel(2, this); // this should be the default panel according to customer/employee
        if (panel == null) {
            throw new RuntimeException("Panel creation failed!");
        }
    }

    @FXML
    void onLoginClick(ActionEvent event) {
        String userName = UserName.getText();
        String userpassword = Password.getText();
        ((CustomerAnonymousPanel) panel).sendCustomerLoginRequest(userName, userpassword);
//        ((CustomerAnonymousPanel) panel).sendCatalogRequestToServer();
    }

    @Subscribe
    public void handleMessageReceivedFromClient(Object msg) {
        System.out.println("message about login was received from server");

        Platform.runLater(()->{
            if(msg.getClass().equals(String.class)){
                if(String.valueOf(msg).equals("client not exist")){
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    a.setHeaderText("שם המשתמש או הסיסמה אינם נכונים");
                    a.setTitle("התחברות");
                    a.setContentText("");
                    a.show();
                    UserName.setText("");
                    Password.setText("");
                }
            }
            else{
                try {
                    App.setRoot("main");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}