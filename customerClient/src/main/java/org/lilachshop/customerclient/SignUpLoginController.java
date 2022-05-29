package org.lilachshop.customerclient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.lilachshop.entities.Customer;

public class SignUpLoginController {


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

        FXMLLoader FinalStagefxmlLoader = new FXMLLoader(CatalogController.class.getResource("SignUp5.fxml"));
        Stage stage = App.getStage();
        Parent root = null;
        root = FinalStagefxmlLoader.load();
        FXMLLoader fxmlLoader = new FXMLLoader(CatalogController.class.getResource("SignUp1.fxml"));
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void initialize() {
        assert logInBtn != null : "fx:id=\"logInBtn\" was not injected: check your FXML file 'SignupLogin.fxml'.";
        assert passwordTF != null : "fx:id=\"passwordTF\" was not injected: check your FXML file 'SignupLogin.fxml'.";
        assert signUpBtn != null : "fx:id=\"signUpBtn\" was not injected: check your FXML file 'SignupLogin.fxml'.";
        assert userNameTF != null : "fx:id=\"userNameTF\" was not injected: check your FXML file 'SignupLogin.fxml'.";

    }

}
