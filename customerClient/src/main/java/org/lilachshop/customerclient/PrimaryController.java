package org.lilachshop.customerclient;

import java.io.IOException;
import java.net.URL;
import java.util.IllegalFormatConversionException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.lilachshop.controller.Controller;
import org.lilachshop.panels.OperationsPanelFactory;
import org.lilachshop.panels.ExamplePanel;
import org.lilachshop.panels.Panel;

public class PrimaryController extends Controller implements Initializable {
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private Button primaryButton;

    @FXML
    private Label message_label;

    @FXML
    private Button msgButton;

    @FXML
    void onSendMessageClick(ActionEvent event) {
        try {
            ((ExamplePanel) panel).sendMessageToServer("example message");
        } catch (Exception e) {
            System.out.println("Conversion operation is not supported.");
            e.printStackTrace();
        }
    }

    @FXML
    void switchToSecondary(ActionEvent event) {
    }

    @Override
//    @Subscribe
    public void handleMessageReceivedFromClient(String msg) {
        Platform.runLater(() -> {
            message_label.setText(msg);
        });
    }

    @FXML
    void initialize() {
        assert message_label != null : "fx:id=\"message_label\" was not injected: check your FXML file 'primary.fxml'.";
        assert msgButton != null : "fx:id=\"msgButton\" was not injected: check your FXML file 'primary.fxml'.";
        assert primaryButton != null : "fx:id=\"primaryButton\" was not injected: check your FXML file 'primary.fxml'.";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventBus.getDefault().register(this);
        panel = OperationsPanelFactory.createPanel(1, this); // this should be the default panel according to customer/employee
        if (panel == null) {
            throw new RuntimeException("Panel creation failed!");
        }
    }
}
