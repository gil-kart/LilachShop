/**
 * Sample Skeleton for 'SignUp4.fxml' Controller Class
 */
package org.lilachshop.customerclient;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.YearMonth;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.lilachshop.events.Signup4Event;

public class SignUpStage4Controller implements Initializable {

    @FXML // fx:id="cardOwnerIDLabel"
    private Text cardOwnerIDLabel; // Value injected by FXMLLoader

    @FXML // fx:id="cardOwnerIDTF"
    private MaskedTextField cardOwnerIDTF; // Value injected by FXMLLoader

    @FXML // fx:id="cardOwnerNameLabel"
    private Text cardOwnerNameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="cardOwnerNameTF"
    private TextField cardOwnerNameTF; // Value injected by FXMLLoader

    @FXML // fx:id="creditCardNumberLabel"
    private Text creditCardNumberLabel; // Value injected by FXMLLoader

    @FXML // fx:id="creditCardNumberTF"
    private MaskedTextField creditCardNumberTF; // Value injected by FXMLLoader

    @FXML // fx:id="endSignUpBtn"
    private Button endSignUpBtn; // Value injected by FXMLLoader

    @FXML // fx:id="btnBack"
    private Button btnBack; // Value injected by FXMLLoader

    @FXML // fx:id="expDateLabel"
    private Text expDateLabel; // Value injected by FXMLLoader

    @FXML // fx:id="expDateTF"
    private MaskedTextField expDateTF; // Value injected by FXMLLoader


    @FXML
    private Text cvcLabel;


    @FXML
    private MaskedTextField cvcTF;


    @FXML
    void onClickEndSignUpBtn(ActionEvent event) {

        Signup4Event finalEvent = new Signup4Event(creditCardNumberTF.getPlainText(), YearMonth.now(),//todo: change to actual card input date
                cardOwnerNameLabel.getText(), Integer.parseInt(cardOwnerIDLabel.getText())); // todo: handle number format exception
        System.out.println("pre-post event4");
        EventBus.getDefault().post(finalEvent);

        Stage stage = CustomerApp.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(CatalogController.class.getResource("SignUp5.fxml"));
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
        FXMLLoader fxmlLoader = new FXMLLoader(CatalogController.class.getResource("Signup3.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        initalizeTextFieldAndLabelConnection(creditCardNumberTF, creditCardNumberLabel);
        initalizeTextFieldAndLabelConnection(cardOwnerIDTF, cardOwnerIDLabel);
        initalizeTextFieldAndLabelConnection(expDateTF, expDateLabel);
        initalizeTextFieldAndLabelConnection(cvcTF,cvcLabel);


        //Handle NameTF
        cardOwnerNameTF.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String curr_val = cardOwnerNameTF.getText();
                cardOwnerNameLabel.setText(curr_val);
            }
        });
    }

    public void initalizeTextFieldAndLabelConnection(MaskedTextField textField, Text label) {
        textField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String curr_val = textField.getMasked_inputed();
                label.setText(curr_val);
            }
        });
    }
}