package org.lilachshop.customerclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import org.lilachshop.customerclient.events.Signup1Event;
import org.lilachshop.customerclient.events.Signup3Event;
import org.lilachshop.entities.AccountType;

import java.io.IOException;

public class SignUpStage3Controller {

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
    private ChoiceBox<?> storeChoiceBox;

    @FXML
    void onClickChainAccount(MouseEvent event) {
        nextBtn.setDisable(false);

        storeAccountHighlight.setVisible(false);
        ChainAccountHighlight.setVisible(true);
        YearlyAccountHighlight.setVisible(false);

        storeChoiceBox.setDisable(false);

    }

    @FXML
    void onClickNextbtn(ActionEvent event) {
        Signup3Event event3 = new Signup3Event(AccountType.CHAIN_ACCOUNT);//TODO:After Adding Stores make it generic
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
        storeAccountHighlight.setVisible(true);
        ChainAccountHighlight.setVisible(false);
        YearlyAccountHighlight.setVisible(false);

        storeChoiceBox.setDisable(false);

    }

    @FXML
    void onClickYearlyAccount(MouseEvent event) {
        nextBtn.setDisable(false);

        storeAccountHighlight.setVisible(false);
        ChainAccountHighlight.setVisible(false);
        YearlyAccountHighlight.setVisible(true);

        storeChoiceBox.setDisable(false);

    }

}
