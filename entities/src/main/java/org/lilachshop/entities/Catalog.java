package org.lilachshop.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
@Transactional
@Entity
@Table(name = "catalog")
public class Catalog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    Store store;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public int getId() {
        return id;
    }
    public void setItems(List<Item> items){ this.items = items;}
    public void addItem(Item item){
        this.items.add(item);
    }

}
