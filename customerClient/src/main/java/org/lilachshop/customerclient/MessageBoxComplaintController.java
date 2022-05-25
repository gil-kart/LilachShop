package org.lilachshop.customerclient;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.lilachshop.panels.OperationsPanelFactory;
import org.lilachshop.panels.Panel;


public class MessageBoxComplaintController implements Initializable {

    static  private Panel panel; // i added that

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField complainAnswerTF;

    @FXML
    private TextField complainDescriptionTF;

    @FXML
    private Text complaintIDTF;

    @FXML
    void initialize() {
        assert complainAnswerTF != null : "fx:id=\"complainAnswerTF\" was not injected: check your FXML file 'messageBoxComplaint.fxml'.";
        assert complainDescriptionTF != null : "fx:id=\"complainDescriptionTF\" was not injected: check your FXML file 'messageBoxComplaint.fxml'.";
        assert complaintIDTF != null : "fx:id=\"complaintIDTF\" was not injected: check your FXML file 'messageBoxComplaint.fxml'.";

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //panel= OperationsPanelFactory.createPanel(); // might use it later
        String complaintID= "654";
        String complaintDescription= "I ordered green Rosses but i got red ones.";
        String complaintAnswer= "My brothaaaaaaaa";
        complainDescriptionTF.setText(complaintDescription);
        complainAnswerTF.setText(complaintAnswer);
        complaintIDTF.setText(complaintID);


    }
}
