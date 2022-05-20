package org.lilachshop.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "catalog")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;

    public int getId() {
        return id;
    }
    public void setItems(List<Item> items){ this.items = items;}
    public void addItem(Item item){
        this.items.add(item);
    }

}
