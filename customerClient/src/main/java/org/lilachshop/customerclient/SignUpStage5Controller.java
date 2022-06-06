package org.lilachshop.customerclient;

import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
        CustomerApp.setMyFlowers(new LinkedList<myOrderItem>());
        CustomerApp.setMyCustomer(registeringCustomer);
        CustomerApp.createPanel();
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
        registeringCustomer.setCard(signupEvent.getCard());

        // alert on charging 100shekels if Annual account
        if(registeringCustomer.getAccountType()==AccountType.ANNUAL_SUBSCRIPTION){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("חיוב עלות מנוי שנתי");
            alert.setHeaderText("נבחר סוג חשבון - מנוי שנתי, אנא אשר חיוב עלות מנוי");
            alert.setResizable(false);
            alert.setContentText(" האם הנך מאשר חיוב בעלות 100 שקלים?");
            alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);

            if (button == ButtonType.OK) {
               alert2.setContentText("עסקה בסך 100 שקלים אושרה");
               alert2.show();
            } else {
                alert2.setContentText("חשבונך התעדכן לחשבון רשת");
                alert2.show();
                registeringCustomer.setAccount(new Account(AccountType.CHAIN_ACCOUNT));
            }
        }
        if (panel.getClass().equals(CustomerAnonymousPanel.class)) {
            CustomerAnonymousPanel customerAnonymousPanel = (CustomerAnonymousPanel) panel;
            System.out.println(panel.getClass());
            System.out.println(registeringCustomer);
            registeringCustomer.setAccountState(ActiveDisabledState.ACTIVE);
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
            panel = OperationsPanelFactory.createPanel(PanelEnum.CUSTOMER_ANONYMOUS,CustomerApp.getSocket(), this);
    }

}
