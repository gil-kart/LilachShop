package org.lilachshop.employeeclient;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.panels.OperationsPanelFactory;
import javafx.scene.text.Text;
import org.lilachshop.panels.Panel;


public class EmployeeLoginController implements Initializable {
    private static Panel panel;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logInBtn;

    @FXML
    private Text errorLogin;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private TextField userNameTF;

    @Subscribe
    public void handleMessageReceivedFromClient(String msg) {
        System.out.println("Got message!");
    }

    @FXML
    void initialize() {
        assert logInBtn != null : "fx:id=\"logInBtn\" was not injected: check your FXML file 'EmployeeLogin.fxml'.";
        assert passwordTF != null : "fx:id=\"passwordTF\" was not injected: check your FXML file 'EmployeeLogin.fxml'.";
        assert userNameTF != null : "fx:id=\"userNameTF\" was not injected: check your FXML file 'EmployeeLogin.fxml'.";

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        panel = OperationsPanelFactory.createPanel(1, this); // will change this

    }


    @FXML
    void tryLogEmployee(ActionEvent event) {
        String userName = userNameTF.getText();
        String password = passwordTF.getText();
        // will be test if there is username && password that matches employee's details at DB
        if (!userName.equals("yossi") || !password.equals("12345")) // In case invalid data
            errorLogin.setVisible(true);
        else {                                                      // will open employeeCatalog window
            errorLogin.setVisible(false);
        }


    }


}
