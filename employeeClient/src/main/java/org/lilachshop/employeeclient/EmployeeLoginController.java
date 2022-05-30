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
import org.lilachshop.entities.Employee;
import org.lilachshop.panels.OperationsPanelFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.lilachshop.panels.Panel;
import org.lilachshop.panels.EmployeeAnonymousPanel;
import org.lilachshop.panels.PanelEnum;


public class EmployeeLoginController implements Initializable {

    static  private Panel panel;

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
        panel = OperationsPanelFactory.createPanel(PanelEnum.GENERAL_EMPLOYEE, this);

    }


    @FXML
    void userNameClick(MouseEvent event) {
        errorLogin.setVisible(false);
    }





    @FXML
    void tryLogEmployee(ActionEvent event) {
        String userName= userNameTF.getText();
        String password= passwordTF.getText();
        ((EmployeeAnonymousPanel) panel).sendLoginRequest(userName, password);

    }

    @Subscribe
    public void handleMessageReceived(Object msg){
        if(msg.getClass().equals(Employee.class)){ // will open Employee dashboard
            System.out.println("WE found you! ! !");
        }

        else{  // couldn't find employee
            errorLogin.setVisible(true);

        }

    }


}
