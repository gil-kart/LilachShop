/**
 * Sample Skeleton for 'SignUp2.fxml' Controller Class
 */

package org.lilachshop.customerclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.lilachshop.commonUtils.Utilities;
import org.lilachshop.entities.Customer;
import org.lilachshop.events.Signup2Event;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SignUpStage2Controller implements Initializable {

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

    Alert alert;


    @FXML
    void onClickNextBtn(ActionEvent event) {
        if (ValidateInput()) {
            Signup2Event event2 = new Signup2Event(firstNameTF.getText(),
                    lastNameTF.getText(), phoneTF.getText(), cityTF.getText(), addressTF.getText());
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

    public boolean ValidateInput() {
        System.out.println(phoneTF.getText().replace("_", ""));
        if (!FieldInHebrewOrDisplayError(firstNameTF, "שם פרטי אינו תקין - אנא מלא שוב בעברית")) {
            return false;
        }
        if (!FieldInHebrewOrDisplayError(lastNameTF, "שם משפחה אינו תקין - אנא מלא שוב בעברית")) {
            return false;
        }
        if (phoneTF.getText().replace("_", "").length() != 11) {
            alert.setContentText("טלפון אינו תקין - אנא מלא שוב");
            alert.show();
            phoneTF.clear();
            return false;
        }
        if (!FieldInHebrewOrDisplayError(cityTF, "שם עיר אינו תקין - אנא מלא שוב בעברית")) {
            return false;
        }
        if(!Utilities.containHebrewOrNumber(addressTF.getText())){
            alert.setContentText("כתובת אינה תקינה - אנא מלא שוב בעברית");
            alert.show();
            addressTF.clear();
            return false;
        }
        return true;
    }


    boolean FieldInHebrewOrDisplayError(TextField F, String msg) {
        if (!Utilities.containHebrew(F.getText())) {
            alert.setContentText(msg);
            alert.show();
            F.clear();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);


    }
}
