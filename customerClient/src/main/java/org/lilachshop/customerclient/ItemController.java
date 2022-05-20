package org.lilachshop.customerclient;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.w3c.dom.events.MouseEvent;

import java.util.Objects;

public class ItemController {
    @FXML
    private ImageView Img;

    @FXML
    private Label Name;

    @FXML
    private Label Price;

    @FXML
    private void click(MouseEvent mouseEvent){
        myListener.onClickListener(flower);
    }

    private MyListener myListener;
    private Flower flower;


    public void setData(Flower flower, MyListener myListener) {
        this.flower = flower;
        this.myListener = myListener;
        Name.setText(flower.getName());
        Price.setText(flower.getPrice());
        try{
            Image image = new Image((Objects.requireNonNull(getClass().getResourceAsStream(flower.getImgSrc()))));
            Img.setImage(image);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void click(javafx.scene.input.MouseEvent mouseEvent) {
        myListener.onClickListener(flower);
    }

    void setPriceInCatalog(Flower flower){
        this.flower.setPrice(flower.getPrice());
        Price.setText(flower.getPrice() );





    }
}
