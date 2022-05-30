/**
 * Sample Skeleton for 'cart.fxml' Controller Class
 */

package org.lilachshop.customerclient;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CartController implements Initializable {
    List<myOrderItem> myFlowers = new ArrayList<>();

    int countItem = 0;

    int sum = 0;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML
    private VBox itemLayout;

    @FXML
    private Label storeName;

    @FXML
    private Label finalPrice;

    @FXML
    private Label count;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="chosenFlower"
    private VBox chosenFlower; // Value injected by FXMLLoader

    @FXML // fx:id="createOrder"
    private Button createOrder; // Value injected by FXMLLoader

    @FXML // fx:id="emptyCart"
    private Button emptyCart; // Value injected by FXMLLoader

    @FXML // fx:id="name"
    private Label name; // Value injected by FXMLLoader

    /**
     * go from cart.fxml to main.fxml
     * load the catalog
     */
    @FXML
    void returnToCatalog(MouseEvent event) {
        Stage stage = App.getStage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CartController.class.getResource("main.fxml"));
            Parent root = fxmlLoader.load();
            CatalogController catalogController = fxmlLoader.getController();
            catalogController.setMyFlowers(myFlowers);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void gotoSignUpOrPersonalArea(MouseEvent event) {

    }

    @FXML
    void onCreateOrder(ActionEvent event) {
        if(Integer.parseInt(count.getText()) > 0)
        {
            Stage stage = App.getStage();
            try {
                FXMLLoader fxmlLoader1 = new FXMLLoader(CartController.class.getResource("OrderStage1.fxml"));
                Parent root = fxmlLoader1.load();
                OrderStage1Controller orderStage1Controller = fxmlLoader1.getController();
                Order myOrder = new Order(myFlowers);
                orderStage1Controller.showInfo(myOrder);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * empty the list of the order
     * clean the board and the text fields
     */
    @FXML
    void onEmptyCart(ActionEvent event) {
        itemLayout.getChildren().clear();
        this.myFlowers.clear();
        this.count.setText("0");
        this.finalPrice.setText("0");

    }

    /**
     * upload the data of the order
     */
    public void showInfo(List<myOrderItem> myFlowers) {
        this.myFlowers = myFlowers;
        for (int i = 0; i < myFlowers.size(); i++) {
            //calculate the sum price of the order
            sum += (myFlowers.get(i).getFlower().getPercent() > 0 ? myFlowers.get(i).getFlower().getPrice() * (100 - myFlowers.get(i).getFlower().getPercent()) / 100 : myFlowers.get(i).getFlower().getPrice())*myFlowers.get(i).getCount();
            //calculate the amount of items that has in the order
            countItem += myFlowers.get(i).getCount();
            //load the item fxml
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("item_cart.fxml"));
            try {
                AnchorPane anchorPane = fxmlLoader.load();
                ItemCartController itemCartController = fxmlLoader.getController();
                //set the photo,name,price and amount from this flower
                itemCartController.setData(myFlowers.get(i));
                itemLayout.getChildren().add(anchorPane);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //set the total price and total amount of products
        finalPrice.setText(String.valueOf(sum));
        count.setText(String.valueOf(countItem));
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EventBus.getDefault().register(this);
    }

    /**
     * handle the eventbus for remove and add the items
     * update the external fields of the item in case of remove and add
     */
    @Subscribe
    public void handleRemoveObject(CartEvent cartEvent) {
        if (cartEvent.action == "remove")
        {
            Platform.runLater(() -> {
                if (cartEvent.object != null) {
                    itemLayout.getChildren().remove(cartEvent.object);
                    myFlowers.remove(cartEvent.updateFlower);
                }
                count.setText(String.valueOf(Integer.parseInt(count.getText())-1));
                if (cartEvent.updateFlower.getFlower().getPercent() > 0)
                    finalPrice.setText(String.valueOf(Integer.parseInt(finalPrice.getText())-(cartEvent.updateFlower.getFlower().getPrice()*(100- cartEvent.updateFlower.getFlower().getPercent())/100)));
                else
                    finalPrice.setText(String.valueOf(Integer.parseInt(finalPrice.getText())- cartEvent.updateFlower.getFlower().getPrice()));
            });
        }
        else if(cartEvent.action == "add")
        {
            count.setText(String.valueOf(Integer.parseInt(count.getText())+1));
            if (cartEvent.updateFlower.getFlower().getPercent() > 0)
                finalPrice.setText(String.valueOf(Integer.parseInt(finalPrice.getText())+(cartEvent.updateFlower.getFlower().getPrice()*(100- cartEvent.updateFlower.getFlower().getPercent())/100)));
            else
                finalPrice.setText(String.valueOf(Integer.parseInt(finalPrice.getText())+ cartEvent.updateFlower.getFlower().getPrice()));
        }
    }
}