/**
 * Sample Skeleton for 'SignUp4.fxml' Controller Class
 */
package org.lilachshop.customerclient;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.customerclient.MaskedTextField;
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
    void cardOwnerIDTyping(InputMethodEvent event) throws ParseException {
    }

    @FXML
    void cardOwnerNameTyping(InputMethodEvent event) {

    }

    @FXML
    void creditCardTyping(InputMethodEvent event) {
        creditCardNumberLabel.setText(creditCardNumberTF.getPlainText());
    }

    @FXML
    void expDateTyping(InputMethodEvent event) {

    }

    @FXML
    void onClickEndSignUpBtn(ActionEvent event) {

        Signup4Event finalEvent = new Signup4Event(creditCardNumberTF.getPlainText(), LocalDate.now(),//todo: change to actual card input date
                cardOwnerNameLabel.getText(), Integer.parseInt(cardOwnerIDLabel.getText())); // todo: handle number format exception
        System.out.println("pre-post event4");
        EventBus.getDefault().post(finalEvent);

        Stage stage = App.getStage();
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
        Stage stage = App.getStage();
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