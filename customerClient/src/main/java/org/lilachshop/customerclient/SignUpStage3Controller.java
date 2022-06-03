package org.lilachshop.customerclient;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.entities.AccountType;
import org.lilachshop.entities.Store;
import org.lilachshop.events.Signup3Event;
import org.lilachshop.panels.CustomerAnonymousPanel;
import org.lilachshop.panels.OperationsPanelFactory;
import org.lilachshop.panels.Panel;
import javafx.beans.value.ChangeListener;
import org.lilachshop.panels.PanelEnum;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SignUpStage3Controller implements Initializable {

    static private Panel panel = null;
    static Store defaultStore = null;
    static Set<Store> allStoresSet = null;
    AccountType chosenAccount;

    @FXML
    private Pane ChainAccountBtn;

    @FXML
    private Rectangle ChainAccountHighlight;

    @FXML
    private ImageView ChainAccountPaneHighlight;

    @FXML
    private Pane StoreAccountBtn;

    @FXML
    private Pane YearlyAccountBtn;

    @FXML
    private Rectangle YearlyAccountHighlight;

    @FXML
    private Text chooseStoreTXT;

    @FXML
    private Button nextBtn;

    @FXML
    private Rectangle storeAccountHighlight;

    @FXML
    private ChoiceBox<Store> storeChoiceBox;

    @FXML
    void onClickNextbtn(ActionEvent event) {

        Signup3Event event3 = new Signup3Event();

        switch (chosenAccount) {
            case STORE_ACCOUNT: {
                event3 = new Signup3Event(AccountType.STORE_ACCOUNT, storeChoiceBox.getSelectionModel().getSelectedItem());
                break;
            }
            case CHAIN_ACCOUNT: {
                event3 = new Signup3Event(AccountType.CHAIN_ACCOUNT, defaultStore);
                break;
            }
            case ANNUAL_SUBSCRIPTION: {
                event3 = new Signup3Event(AccountType.ANNUAL_SUBSCRIPTION, defaultStore);
                break;
            }
        }
        EventBus.getDefault().post(event3);

        Stage stage = App.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(CatalogController.class.getResource("SignUp4.fxml"));
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
        FXMLLoader fxmlLoader = new FXMLLoader(CatalogController.class.getResource("Signup2.fxml"));
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
    void onClickStoreAccountBtn(MouseEvent event) {
        nextBtn.setDisable(true);
        storeAccountHighlight.setVisible(true);
        ChainAccountHighlight.setVisible(false);
        YearlyAccountHighlight.setVisible(false);

        storeChoiceBox.setDisable(false);
        chosenAccount = AccountType.STORE_ACCOUNT;

    }

    @FXML
    void onClickChainAccount(MouseEvent event) {
        nextBtn.setDisable(false);

        storeAccountHighlight.setVisible(false);
        ChainAccountHighlight.setVisible(true);
        YearlyAccountHighlight.setVisible(false);

        storeChoiceBox.setDisable(true);
        storeChoiceBox.getSelectionModel().clearSelection();
        chosenAccount = AccountType.CHAIN_ACCOUNT;
    }

    @FXML
    void onClickYearlyAccount(MouseEvent event) {
        nextBtn.setDisable(false);

        storeAccountHighlight.setVisible(false);
        ChainAccountHighlight.setVisible(false);
        YearlyAccountHighlight.setVisible(true);

        storeChoiceBox.setDisable(true);
        storeChoiceBox.getSelectionModel().clearSelection();
        chosenAccount = AccountType.ANNUAL_SUBSCRIPTION;
    }

    @Subscribe
    public void onGetAllStores(List<Store> allStores) {
        defaultStore = defaultStore == null ? allStores.get(0) : defaultStore;
        allStoresSet = allStoresSet == null ? new HashSet<>(allStores) : allStoresSet;
        Platform.runLater(() -> {
            storeChoiceBox.setItems(FXCollections.observableArrayList(allStoresSet));
            System.out.println("num of items in choice box:" + storeChoiceBox.getItems().size());

        });

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (panel == null)
            panel = OperationsPanelFactory.createPanel(PanelEnum.CUSTOMER_ANONYMOUS, this);

        CustomerAnonymousPanel customerAnonymousPanel = (CustomerAnonymousPanel) panel;
        if (customerAnonymousPanel == null) {
            throw new RuntimeException("Bad panel output in Signup Stage 3 controller");
        }

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    nextBtn.setDisable(false);
                }
            }
        };

        if (allStoresSet == null) {
            customerAnonymousPanel.getAllStores();
        } else {
            storeChoiceBox.setItems(FXCollections.observableArrayList(allStoresSet));
            System.out.println("num of items in choice box:" + storeChoiceBox.getItems().size());
        }
        storeChoiceBox.getSelectionModel().selectedItemProperty().addListener(changeListener);

    }
}
