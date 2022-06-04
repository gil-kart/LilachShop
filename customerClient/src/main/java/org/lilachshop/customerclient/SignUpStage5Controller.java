package org.lilachshop.customerclient;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.entities.*;
import org.lilachshop.events.Signup1Event;
import org.lilachshop.events.Signup2Event;
import org.lilachshop.events.Signup3Event;
import org.lilachshop.events.Signup4Event;
import org.lilachshop.panels.*;

public class SignUpStage5Controller {

    static private Panel panel = null;
    EventBus signupBus = null;
    Customer registeringCustomer;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backCatalogBtn;

    @FXML
    void onClickBackCatalogBtn(ActionEvent event) {
        App.setMyFlowers(new LinkedList<myOrderItem>());
        App.setMyCustomer(registeringCustomer);
        App.CreatePanel();
    }


    @Subscribe
    public void onSignUp1Event(Signup1Event signupEvent) {
        registeringCustomer.setUserName(signupEvent.getUsername());
        registeringCustomer.setUserPassword(signupEvent.getPassword());
        System.out.println("gotEvent1");
    }

    @Subscribe
    public void onSignUp2Event(Signup2Event signupEvent) {
        registeringCustomer.setName(signupEvent.getFirstName() + " " + signupEvent.getLastName());
        try {
            registeringCustomer.setPhoneNumber(signupEvent.getPhoneNumber());
        } catch (Exception ignored) {
        }
        registeringCustomer.setAddress(signupEvent.getCity() + ", " + signupEvent.getAddress());
        System.out.println("gotEvent2");
    }

    @Subscribe
    public void onSignUp3Event(Signup3Event signupEvent) {
        registeringCustomer.setAccount(new Account(signupEvent.getChosenAccount()));
        registeringCustomer.setStore(signupEvent.getStore());
        System.out.println("gotEvent3");
    }


    @Subscribe
    public void onSignUp4Event(Signup4Event signupEvent) {
        registeringCustomer.setCard(new CreditCard(signupEvent.getCardNumber(), signupEvent.getExpDate(), signupEvent.getCardOwnerName(), String.valueOf(signupEvent.getCardOwnerID()), "123"));
        if (panel.getClass().equals(CustomerAnonymousPanel.class)) {

            CustomerAnonymousPanel customerAnonymousPanel = (CustomerAnonymousPanel) panel;
            System.out.println(panel.getClass());
            System.out.println(registeringCustomer);
            customerAnonymousPanel.sendSignUpRequest(registeringCustomer);
            System.out.println("gotEvent4");
            registeringCustomer = null;
            EventBus.getDefault().unregister(this);
        }
    }


    @FXML
    void initialize() {

        assert backCatalogBtn != null : "fx:id=\"backCatalogBtn\" was not injected: check your FXML file 'SignUp5.fxml'.";
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);


        registeringCustomer = registeringCustomer == null ? new Customer() : registeringCustomer;
        if (panel == null)
            panel = OperationsPanelFactory.createPanel(PanelEnum.CUSTOMER_ANONYMOUS, this);
    }

}
