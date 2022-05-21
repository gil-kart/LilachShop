package org.lilachshop.employeeclient;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CustomerServiceViewController implements Initializable {
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

        // complaints will be sent from the server!
        setListOfComplaints();

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

    private void setListOfComplaints() {
        listOfComplaints = FXCollections.observableArrayList();
        listOfComplaints.add(new Complaint("13.1.22", "פתוח", "321", "איזה שירות גרוע"));
        listOfComplaints.add(new Complaint("15.1.22", "פתוח", "322", "איזה שירות חרבנה"));
        listOfComplaints.add(new Complaint("14.1.22", "פתוח", "323", "איזה פרחים מכוערים"));
        listOfComplaints.add(new Complaint("13.1.22", "פתוח", "444", "איזה שירות נורא"));
        listOfComplaints.add(new Complaint("17.1.22", "פתוח", "456", "איזה שירות מזוויע"));
        listOfComplaints.add(new Complaint("14.1.22", "פתוח", "668", "איזה פרחים מגעילים"));
    }

    public void closeComplaint(String updatedComplaintNumber){
        //todo: needs to update complaint status also in the database
        for (Complaint complaint : listOfComplaints) {
            if(complaint.getComplaintNumber().equals(updatedComplaintNumber)){
                complaint.setStatus("סגור");
                break;
            }
        }
        tableView.setItems(listOfComplaints);
        tableView.refresh();
    }

    private void presentRowSelected() throws IOException {
        ObservableList<Complaint> listOfComplaints = tableView.getSelectionModel().getSelectedItems();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("complaintWorkerResponse.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        ComplaintWorkerResponseController controller = fxmlLoader.getController();
        controller.setComplaintData(
                listOfComplaints.get(0).complaintNumber,
                listOfComplaints.get(0).status,
                listOfComplaints.get(0).endOfHandleDate,
                listOfComplaints.get(0).content, this);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Present Selected Row");
        stage.setScene(new Scene(root1));
        stage.show();
    }
}


