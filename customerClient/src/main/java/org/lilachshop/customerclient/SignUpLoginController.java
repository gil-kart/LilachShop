package org.lilachshop.customerclient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.entities.Customer;
import org.lilachshop.panels.CustomerAnonymousPanel;
import org.lilachshop.panels.OperationsPanelFactory;
import org.lilachshop.panels.Panel;
import org.lilachshop.panels.PanelEnum;

public class SignUpLoginController implements Initializable {

    private static Panel panel;



    static FXMLLoader FinalStagefxmlLoader = null;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logInBtn;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private Button signUpBtn;

    @FXML
    private TextField userNameTF;

    @FXML
    void onClickSignupBtn(ActionEvent event) throws IOException {

        Stage stage = App.getStage();
        Parent root = null;

        if (FinalStagefxmlLoader == null) {
            FinalStagefxmlLoader = new FXMLLoader(getClass().getResource("SignUp5.fxml"));
            FinalStagefxmlLoader.load();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUp1.fxml"));
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void onLoginClick(ActionEvent event) {
        String userName = userNameTF.getText();
        String userpassword = passwordTF.getText();
        ((CustomerAnonymousPanel) panel).sendCustomerLoginRequest(userName, userpassword);
//        ((CustomerAnonymousPanel) panel).sendCatalogRequestToServer();
    }

    @FXML
    void initialize() {
        assert logInBtn != null : "fx:id=\"logInBtn\" was not injected: check your FXML file 'SignupLogin.fxml'.";
        assert passwordTF != null : "fx:id=\"passwordTF\" was not injected: check your FXML file 'SignupLogin.fxml'.";
        assert signUpBtn != null : "fx:id=\"signUpBtn\" was not injected: check your FXML file 'SignupLogin.fxml'.";
        assert userNameTF != null : "fx:id=\"userNameTF\" was not injected: check your FXML file 'SignupLogin.fxml'.";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        panel = OperationsPanelFactory.createPanel(PanelEnum.CUSTOMER_ANONYMOUS, this); //TODO:make sure to change to Enum when code pushed
        if (panel == null) {
            throw new RuntimeException("Panel creation failed!");
        }
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
                    userNameTF.setText("");
                    passwordTF.setText("");
                }
            } else {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    CatalogController controller = fxmlLoader.getController();
                    controller.setData(((Customer) msg));

                    Stage stage = App.getStage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    });
    }
}
