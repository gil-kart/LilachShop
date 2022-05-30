package org.lilachshop.employeeclient;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.entities.Complaint;
import org.lilachshop.entities.Item;
import org.lilachshop.panels.CustomerServicePanel;
import org.lilachshop.panels.OperationsPanelFactory;
import org.lilachshop.panels.Panel;
import org.lilachshop.panels.PanelEnum;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class CustomerServiceViewController implements Initializable {
    private static Panel panel;
    @FXML
    private TableView<Complaint> tableView;

    @FXML
    private TableColumn<Complaint, String> complaintNumber;

    @FXML
    private TableColumn<Complaint, String> content;

    @FXML
    private TableColumn<Complaint, String> creationDate;

    @FXML
    private TableColumn<Complaint, String> status;

    ObservableList<Complaint> listOfComplaints;

    @Override
    public void initialize(URL url, ResourceBundle rb){

        complaintNumber.setCellValueFactory(new PropertyValueFactory<Complaint, String>("complaintNumber"));
        content.setCellValueFactory(new PropertyValueFactory<Complaint, String>("content"));
        creationDate.setCellValueFactory(new PropertyValueFactory<Complaint, String>("creationDate"));
        status.setCellValueFactory(new PropertyValueFactory<Complaint, String>("status"));
        panel = OperationsPanelFactory.createPanel(PanelEnum.CUSTOMER_SERVICE, this); // this should be the default panel according to customer/employee
        if (panel == null) {
            throw new RuntimeException("Panel creation failed!");
        }
        // send request to server to get all existing complaints
        ((CustomerServicePanel) panel).GetAllClientsComplaintsRequestToServer();

    }

    private void setListOfComplaints(List<Complaint> complaintsFromServer) {
        listOfComplaints = FXCollections.observableArrayList();
        listOfComplaints.addAll(complaintsFromServer);
        tableView.setEditable(true);
        tableView.setItems(listOfComplaints);
        tableView.setOnMouseClicked(e ->{
            try {
                presentRowSelected();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void closeComplaint(String updatedComplaintNumber, String reply){
        //todo: needs to update complaint status also in the database
        for (Complaint complaint : listOfComplaints) {
            if(String.valueOf(complaint.getId()).equals(updatedComplaintNumber)){
                complaint.setStatus("סגור");
                // close complaint on server
                ((CustomerServicePanel) panel).ReplyToComplaintRequestToServer(complaint, reply);
                break;
            }
        }
        tableView.setItems(listOfComplaints);
        tableView.refresh();
    }

    private void presentRowSelected() throws IOException {
        if (listOfComplaints.isEmpty())
            return;
        ObservableList<Complaint> listOfComplaints = tableView.getSelectionModel().getSelectedItems();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("complaintWorkerResponse.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        ComplaintWorkerResponseController controller = fxmlLoader.getController();

        try{
            controller.setComplaintData(
                    String.valueOf(listOfComplaints.get(0).getId()),
                    listOfComplaints.get(0).getStatus(),
                    listOfComplaints.get(0).getCreationDate(),
                    listOfComplaints.get(0).getContent(), this);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Present Selected Row");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @Subscribe
    public void handleComplaintsReceivedFromClient(Object msg) {
        System.out.println("Complaints recieved from server");
        Platform.runLater(()->{
            setListOfComplaints((List<Complaint>)msg);
        });
    }

}


