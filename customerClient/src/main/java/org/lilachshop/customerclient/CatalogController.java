/**
 * Sample Skeleton for 'main.fxml' Controller Class
 */

package org.lilachshop.customerclient;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.greenrobot.eventbus.Subscribe;
import org.lilachshop.entities.AccountType;
import org.lilachshop.entities.Customer;
import org.lilachshop.entities.Item;
import org.lilachshop.entities.Store;
import org.lilachshop.panels.*;

public class CatalogController {
    private static Panel panel;
    Boolean switchFlag = false;
    private Item flowerShown;

    public void setMyFlowers(List<myOrderItem> myFlowers) {
        this.myFlowers = myFlowers;
    }

    private MyListener myListener;
    private List<Item> flowerList = new ArrayList<>();
    private List<myOrderItem> myFlowers = null;
    List<ItemController> itemControllers = new ArrayList<>();
    int count = 0;
    private static final int MAX_ON_SALE = 10;
    Customer customer = null;
    long storeId;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @FXML
    private Label history;

    @FXML
    private ImageView historyImg;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private HBox itemLayout;

    @FXML // fx:id="FlowerImg"
    private ImageView FlowerImg; // Value injected by FXMLLoader

    @FXML // fx:id="FlowerNameLabel"
    private Label FlowerNameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="FlowerPrice"
    private Label FlowerPrice; // Value injected by FXMLLoader

    @FXML // fx:id="addToCart"
    private Button addToCart; // Value injected by FXMLLoader

    @FXML // fx:id="chosenFlower"
    private VBox chosenFlower; // Value injected by FXMLLoader

    @FXML // fx:id="countItems"
    private Label countItems; // Value injected by FXMLLoader


    @FXML
    private FlowPane grid;

    @FXML // fx:id="name"
    private Label name; // Value injected by FXMLLoader

    @FXML // fx:id="oldPrice"
    private Text oldPrice; // Value injected by FXMLLoader

    @FXML // fx:id="oldPrice1"
    private Text oldPrice1; // Value injected by FXMLLoader

    @FXML // fx:id="saleImag"
    private ImageView saleImag; // Value injected by FXMLLoader

    @FXML // fx:id="scroll"
    private ScrollPane scroll; // Value injected by FXMLLoader

    @FXML // fx:id="shopList"
    private ChoiceBox<?> shopList; // Value injected by FXMLLoader

    /**
     * set the new scene cart
     * and sent the order list
     */
    @FXML
    void gotoCart(MouseEvent event) {
        if (myFlowers.size() > 0) {
            Stage stage = App.getStage();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(CatalogController.class.getResource("cart.fxml"));
                Parent root = fxmlLoader.load();
                CartController cartController = fxmlLoader.getController();
                cartController.showInfo(myFlowers);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
        {
            Alert a = new Alert(Alert.AlertType.NONE);
            if (!switchFlag)
            {
                ButtonType button = new ButtonType("הרשם/התחבר");
                a.getButtonTypes().setAll(button);
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setHeaderText("נא התחבר או הרשם למערכת");
                a.setTitle("הוספה לסל");
            }
            else
            {
                ButtonType button = new ButtonType("אישור");
                a.getButtonTypes().setAll(button);
                a.setHeaderText("הסל ריק נא הוסף מוצרים לסל");
                a.setTitle("הוספה לסל");
            }
            a.setContentText("");
            a.show();
        }
    }

    @FXML
    void openPersonalArea(ActionEvent event) {

    }

    public void gotoHistory(MouseEvent event) {
        try {
            Stage stage = App.getStage();
            FXMLLoader fxmlLoader = new FXMLLoader(CatalogController.class.getResource("test.fxml"));
            Parent root = fxmlLoader.load();
            stage.setScene(new Scene(root));
            stage.show();
        }catch(IOException e)
        {

        }
    }

    @FXML
    void gotoSignUp(MouseEvent event) {
        if(!switchFlag)
        {
            switchFlag = true;
            name.setText("שלום, "+ "יוסי לוי");
            history.setVisible(true);
            historyImg.setVisible(true);
        }
        else
        {

        }

    }

    /**
     * add the item to the list
     * if exit increase the amount else create new
     */
    @FXML
    void onAddToCart(ActionEvent event) {
        boolean addFlag = false;
        if (switchFlag)
        {
            for (myOrderItem flower : myFlowers)
            {
              if(flowerShown == flower.getFlower()) {
                  flower.setCount(flower.getCount() + 1);
                  addFlag = true;
              }
            }
            if(!addFlag)
            {
                myOrderItem newFlower = new myOrderItem(flowerShown,1);
                myFlowers.add(newFlower);
            }
            count++;
            countItems.setText(String.valueOf(count));
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.NONE);
            ButtonType button = new ButtonType("הרשם/התחבר");
            a.getButtonTypes().setAll(button);
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setHeaderText("נא התחבר או הרשם למערכת");
            a.setTitle("הוספה לסל");
            a.setContentText("");
            a.show();

        }
    }

    @FXML
    void onFilter(MouseEvent event) {

    }

    @FXML
    void openShopList(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        count = 0;
        shopList.setDisable(true);
        myFlowers = new ArrayList<>();
           //for (myOrderItem flower : myFlowers)
             //   count += flower.getCount();
        countItems.setText(String.valueOf(count));
            panel = OperationsPanelFactory.createPanel(PanelEnum.CUSTOMER_ANONYMOUS,this);
            ((CustomerAnonymousPanel) panel).sendGetGeneralCatalogRequestToServer();


        //flowerList = this.getItemList();

    }

    /**
     * set the chosen item from the catalog in the fields on the left side of the scene
     */
    public void setChosenItem(Item flower) {
        FlowerNameLabel.setText(flower.getName());

        if (flower.getPercent()>0)
        {
            System.out.println(flower.getPercent()>0);
            oldPrice.setVisible(true);
            oldPrice.setText(String.valueOf(flower.getPrice()));
            FlowerPrice.setText(String.valueOf(flower.getPrice()*(100-flower.getPercent())/100));
            oldPrice1.setVisible(true);
            saleImag.setVisible(true);

        }
        else
        {
            FlowerPrice.setText(String.valueOf(flower.getPrice()));
            oldPrice.setVisible(false);
            oldPrice1.setVisible(false);
            saleImag.setVisible(false);
        }
        try {
            FlowerImg.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(flower.getImage()))));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        flowerShown = flower;
    }
    @Subscribe
    public void handleMessageReceivedFromClient(List<Item> msg) {
        flowerList = msg;
        int countOnSale = 0;
        System.out.println("catalogController recieved message from server");
        if (flowerList.size() > 0) {
            setChosenItem(flowerList.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Item flower) {
                    setChosenItem(flower);
                }
            };
        }
        try {
            for (Item flower : flowerList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(flower, myListener);
                Platform.runLater(()->{grid.getChildren().add(anchorPane);});//, column++, row);

                //GridPane.setMargin(anchorPane, new Insets(5));

                if (countOnSale <= MAX_ON_SALE && flower.getPercent() > 0) {
                    try {
                        FXMLLoader fxmlLoaderSale = new FXMLLoader();
                        fxmlLoaderSale.setLocation(getClass().getResource("saleItem.fxml"));
                        AnchorPane anchorPaneSale = fxmlLoaderSale.load();

                        SaleItemController saleItemController = fxmlLoaderSale.getController();
                        saleItemController.setData(flower, myListener);
                        Platform.runLater(()->{itemLayout.getChildren().add(anchorPaneSale);});
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e)
        {

        }
    }

    public void setData(Customer msg) {
            switchFlag = true;
            this.customer = msg;
            name.setText("שלום, "+ customer.getName());
            history.setVisible(true);
            historyImg.setVisible(true);
            AccountType userAccountType = customer.getAccount().getAccountType();
            if (userAccountType.equals(AccountType.CHAIN_ACCOUNT)){
                panel = OperationsPanelFactory.createPanel(PanelEnum.CHAIN_CUSTOMER,this);
                storeId = customer.getStore().getId();
                // todo: implement enable store combo box!
                shopList.setDisable(false);
            }
            else if (userAccountType.equals(AccountType.STORE_ACCOUNT)){
                panel = OperationsPanelFactory.createPanel(PanelEnum.STORE_CUSTOMER,this);
                storeId = customer.getStore().getId();
            }
            else{
                panel = OperationsPanelFactory.createPanel(PanelEnum.ANNUAL_CUSTOMER,this);
                storeId = customer.getStore().getId();
            }
            ((StoreCustomerPanel) panel).sendGetCatalogRequestToServer(storeId);
    }
}
