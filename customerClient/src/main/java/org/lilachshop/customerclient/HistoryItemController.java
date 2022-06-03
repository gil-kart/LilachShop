package org.lilachshop.customerclient; /**
 * Sample Skeleton for 'historyItem.fxml' Controller Class
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import org.lilachshop.entities.Item;
import org.lilachshop.entities.Order;
import org.lilachshop.entities.myOrderItem;

public class HistoryItemController {


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

    public void setData(Order order) {
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
    }

    public void onCencelOrder(ActionEvent event) {
    }

    public void onFilingComplaint(ActionEvent event) {
    }
}
