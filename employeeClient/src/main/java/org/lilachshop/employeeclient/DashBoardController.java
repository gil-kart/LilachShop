package org.lilachshop.employeeclient;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class DashBoardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox catalogBtn;

    @FXML
    private HBox complaintsBtn;

    @FXML
    private HBox costumerBtn;

    @FXML
    private Pane displayer;

    @FXML
    private HBox reportBtn;

    @FXML
    private BorderPane mainPane;

    @FXML
    void employeeBtn(MouseEvent event) throws IOException {

    }

    @FXML
    void onClickCatalogBtn(MouseEvent event) {

    }

    @FXML
    void onClickComplaintsBtn(MouseEvent event) {

    }

    @FXML
    void onClickCostumerBtn(MouseEvent event) {

    }

    @FXML
    void onClickReportBtn(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert catalogBtn != null : "fx:id=\"catalogBtn\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert complaintsBtn != null : "fx:id=\"complaintsBtn\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert costumerBtn != null : "fx:id=\"costumerBtn\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert displayer != null : "fx:id=\"displayer\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert reportBtn != null : "fx:id=\"reportBtn\" was not injected: check your FXML file 'dashboard.fxml'.";



    }

}
