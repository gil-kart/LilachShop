/**
 * Sample Skeleton for 'SignUp2.fxml' Controller Class
 */

package org.lilachshop.customerclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.lilachshop.events.Signup2Event;

import java.io.IOException;

public class SignUpStage2Controller {

    @FXML // fx:id="addressTF"
    private TextField addressTF; // Value injected by FXMLLoader

    @FXML // fx:id="cityTF"
    private TextField cityTF; // Value injected by FXMLLoader

    @FXML // fx:id="firstNameTF"
    private TextField firstNameTF; // Value injected by FXMLLoader

    @FXML // fx:id="lastNameTF"
    private TextField lastNameTF; // Value injected by FXMLLoader

    @FXML // fx:id="nextBtn"
    private Button nextBtn; // Value injected by FXMLLoader

    @FXML // fx:id="phoneTF"
    private MaskedTextField phoneTF; // Value injected by FXMLLoader

    @FXML
    void onClickNextBtn(ActionEvent event) {

        Signup2Event event2 = new Signup2Event(firstNameTF.getText(),
                lastNameTF.getText(),phoneTF.getText(),cityTF.getText(),addressTF.getText());
        EventBus.getDefault().post(event2);
        Stage stage = CustomerApp.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(CatalogController.class.getResource("SignUp3.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void onClickBtnBack(ActionEvent event) {
        Stage stage = CustomerApp.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(CatalogController.class.getResource("Signup1.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.show();
    }

}
