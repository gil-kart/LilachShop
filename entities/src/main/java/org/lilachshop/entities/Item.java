package org.lilachshop.entities;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Items")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "item_name")
    private String name;

    private int price;
    private String image;

    @ManyToOne
    private Catalog catalog;


    public Item() {
    }

    @Override
    public String toString() {
        return "item name: " + name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item(String name, int price, String image) {
        super();
        this.name = name;
        this.price = price;
        this.image = image;

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
