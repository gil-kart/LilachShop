package org.lilachshop.customerclient; /**
 * Sample Skeleton for 'historyItem.fxml' Controller Class
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.lilachshop.entities.Item;
import org.lilachshop.entities.Order;
import org.lilachshop.entities.myOrderItem;

import java.io.IOException;

public class HistoryItemController {
    private Order order;
    @FXML
    private Button fillComplaint;
    @FXML
    private HBox layout;

    @FXML
    private Label orderNum;

    @FXML
    private Label price;

    @FXML
    private Label status;

    @FXML
    private Circle statusColor;

    @FXML
    private Button ComplaintReply;

    public void setData(Order order) {
        this.order = order;
        for (myOrderItem itemsType : order.getItems())
        {
            for(int i = 0; i < itemsType.getCount(); i++)
            {
                ImageView img = new ImageView();
                img.setImage(new Image(itemsType.getItem().getImage()));
                img.setFitWidth(70);
                img.setFitHeight(70);
                layout.getChildren().add(img);
            }
            //status.setText(order.);
            orderNum.setText("# "+ order.getId());
            price.setText(Double.toString(order.getTotalPrice()));
        }
    }

    public void onComplaintReply(ActionEvent event) {
        Stage stage = App.getStage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MessageBoxComplaint.fxml"));
            Parent root = fxmlLoader.load();
            MessageBoxComplaintController controller = fxmlLoader.getController();
            stage.setScene(new Scene(root));
            stage.show();
            long complaintID = order.getComplaint().getId();
            controller.setData(complaintID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onCencelOrder(ActionEvent event) {
    }

    public void onFilingComplaint(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("complaintForm.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        ComplaintController controller = fxmlLoader.getController();
        controller.setData(order, this);
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }
    public void disablePostComplaintBtn(){
        fillComplaint.setDisable(true);
    }
}
