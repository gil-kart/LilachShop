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
import org.lilachshop.entities.Item;
import org.lilachshop.panels.CustomerAnonymousPanel;
import org.lilachshop.panels.OperationsPanelFactory;
import org.lilachshop.panels.Panel;

public class CatalogController {
    private static Panel panel;
    Boolean switchFlag = false;
    private Item flowerShown;
    private MyListener myListener;
    private List<Item> flowerList = new ArrayList<>();
    private List<myOrderItem> myFlowers;
    List<ItemController> itemControllers = new ArrayList<>();
    int count = 0;
    private static final int MAX_ON_SALE = 10;

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
    private ComboBox<?> shopList; // Value injected by FXMLLoader

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
        panel = OperationsPanelFactory.createPanel(2, this);
        if (panel == null) {
            throw new RuntimeException("Panel creation failed!");
        }
        ((CustomerAnonymousPanel) panel).sendCatalogRequestToServer();
        myFlowers = new ArrayList<>();
        //flowerList = this.getItemList();
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
            int countOnSale = 0;
            for (Item flower : flowerList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemControllers.add(itemController);
                itemController.setData(flower, myListener);

                grid.getChildren().add(anchorPane);//, column++, row);
                GridPane.setMargin(anchorPane, new Insets(5));

                if(countOnSale <= MAX_ON_SALE && flower.getPercent() > 0)
                {
                    try {
                        FXMLLoader fxmlLoaderSale = new FXMLLoader();
                        fxmlLoaderSale.setLocation(getClass().getResource("saleItem.fxml"));
                        AnchorPane anchorPaneSale = fxmlLoaderSale.load();

                        SaleItemController saleItemController = fxmlLoaderSale.getController();
                        saleItemController.setData(flower,myListener);
                        itemLayout.getChildren().add(anchorPaneSale);
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    /*private List<Flower> getItemList() {
        Flower flower;
        List<Flower> flowerList = new ArrayList<>();
        itemLayout.getChildren().clear();
        String base_path = "/images/";
        flower = new Flower("סחלב קורל", 160, base_path + "sahlav_coral.jpg",0);
        flowerList.add(flower);
        flower = new Flower("ורד ענבר", 120, base_path + "vered_inbar.jpg",10);
        flowerList.add(flower);
        flower = new Flower("סחלב לבן", 140, base_path + "sahlav_lavan.jpg",0);
        flowerList.add(flower);
        flower = new Flower("נרקיס חצוצרה", 110, base_path + "narkis_hatsostra.jpg",0);
        flowerList.add(flower);
        flower = new Flower("רקפות", 100, base_path + "cyclamen.jpg",30);
        flowerList.add(flower);
        flower = new Flower("קקטוס", 70, base_path + "cactus.jpg",0);
        flowerList.add(flower);
        flower = new Flower("תורמוס", 200, base_path + "lupins.jpg",0);
        flowerList.add(flower);
        flower = new Flower("חמניות", 170, base_path + "heilanthus.jpg",15);
        flowerList.add(flower);
        flower = new Flower("חינניות", 125, base_path + "daisy.jpg",0);
        flowerList.add(flower);
        flower = new Flower("אדמוניות", 190, base_path + "peonybouquet.jpg",0);
        flowerList.add(flower);
        flower = new Flower("צבעוני", 175, base_path + "orange_tulips.jpg",5);
        flowerList.add(flower);
        flower = new Flower("פרג", 180, base_path + "poppy.jpg",0);
        flowerList.add(flower);
        flower = new Flower("סוקולנטים", 100, base_path + "succulents.jpg",0);
        flowerList.add(flower);
        flower = new Flower("שושן", 90, base_path + "lily.jpg",0);
        flowerList.add(flower);
        return flowerList;
    }*/

    /**
     * set the scene catalog
     */
    public void showInfo(List<myOrderItem> myFlowers) {
        count = 0;
        shopList.setDisable(true);
        this.myFlowers = myFlowers;
        for (myOrderItem flower : myFlowers)
            count += flower.getCount();
        if(count > 0) {
            switchFlag = true;
            name.setText("שלום, "+ "יוסי לוי");
            history.setVisible(true);
            historyImg.setVisible(true);
        }
        countItems.setText(String.valueOf(count));
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
}
