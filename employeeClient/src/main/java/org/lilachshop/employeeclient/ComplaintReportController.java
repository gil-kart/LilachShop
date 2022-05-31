package org.lilachshop.employeeclient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.entities.Complaint;
import org.lilachshop.panels.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

// todo: solve all the errors when changing stores and dates
public class ComplaintReportController implements Initializable {
    private static Panel panel;
    List<Complaint> complaints;
    @FXML
    private Button newScreenBtn;

    @FXML
    private BarChart<?, ?> complaintBarChart;

    @FXML
    private NumberAxis complantNumber;

    @FXML
    private CategoryAxis days;

    @FXML
    private DatePicker endDate;

    @FXML
    private Button okBtn;

    @FXML
    private DatePicker startDate;
    @FXML
    private ComboBox<String> storeList;

    @FXML
    private Label chooseStoreLabel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        panel = OperationsPanelFactory.createPanel(PanelEnum.CHAIN_MANAGER, this);
//        panel = OperationsPanelFactory.createPanel(PanelEnum.STORE_MANAGER, this);
        if(panel.getClass().equals(ChainManagerPanel.class)){
        ((ChainManagerPanel) panel).getStoreComplaint(1);
        }
        else {
            ((StoreManagerPanel) panel).getStoreComplaint(1);
            storeList.setVisible(false);
            chooseStoreLabel.setVisible(false);
            newScreenBtn.setVisible(false);
        }
        storeList.getItems().addAll("לילך חיפה", "לילך תל אביב", "לילך הרצליה", "לילך עכו", "לילך באר שבע");
        //todo: if chain manger is logged in, do haifa, if store manger logged in, do store managers store
        storeList.promptTextProperty().set("לילך חיפה");
    }
    @FXML
    void updateBarChart(ActionEvent event) {
        LocalDate start = startDate.getValue();
        LocalDate end = endDate.getValue();
        if(start == null || end == null){
            displayNullAlert();
            return;
        }
        if(end.isBefore(start)){
            displayChronologyAlert();
            return;
        }

        complaintBarChart.getData().clear();
        complaintBarChart.getXAxis().animatedProperty().set(false);

        int todayComplaintCounter=0;
        XYChart.Series set = new XYChart.Series<>();
        for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1))
        {
            for(Complaint complaint: complaints){
                if(complaint.getCreationDate().equals(date)){
                    todayComplaintCounter++;
                }
            }
            set.getData().add(new XYChart.Data(date.toString() , todayComplaintCounter));
            complaintBarChart.getData().addAll(set);
            todayComplaintCounter=0;
        }
    }

    private void displayChronologyAlert() {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setHeaderText("יש לבחור תאריך התחלה מוקדם יותר מתאריך הסיום");
        a.setTitle("בחירת טווח תאריכים");
        a.setContentText("");
        a.show();
    }

    private void displayNullAlert() {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setHeaderText("יש לבחור תאריך התחלה וסיום!");
        a.setTitle("בחירת טווח תאריכים");
        a.setContentText("");
        a.show();
    }
    @FXML
    void onChangeStore(ActionEvent event) {
        String selectedStore = storeList.getSelectionModel().getSelectedItem();
        complaintBarChart.getData().clear();
        startDate.setValue(null);
        endDate.setValue(null);
        //todo: get complaints from all stores
        if(selectedStore.equals("לילך הרצליה")){
            ((ChainManagerPanel) panel).getStoreComplaint(2);
        }
        else if(selectedStore.equals("לילך חיפה")){
            ((ChainManagerPanel) panel).getStoreComplaint(1);
        }
    }
    @FXML
    void onNewScreenBtnClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ComplaintReport.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @Subscribe
    public void handleMessageFromClient(List<Complaint> complaints){
        this.complaints = complaints;
    }
}

