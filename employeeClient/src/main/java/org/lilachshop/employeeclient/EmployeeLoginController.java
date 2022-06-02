package org.lilachshop.employeeclient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
        panel = OperationsPanelFactory.createPanel(PanelEnum.EMPLOYEE_ANONYMOUS, this);

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
        Platform.runLater(()-> {
                if (msg.getClass().equals(Employee.class)) { // will open Employee dashboard
                    try {
                        System.out.println("WE found you! ! !"); // DELETE AFTER
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        DashBoardController controller = fxmlLoader.getController();
                        controller.employee = ((Employee)msg);
                        controller.setData((Employee)msg);
                        Stage stage = App.getStage();
                        stage.setScene(new Scene(root1));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {  // couldn't find employee
                    errorLogin.setVisible(true);
                }
        });
    }


}
