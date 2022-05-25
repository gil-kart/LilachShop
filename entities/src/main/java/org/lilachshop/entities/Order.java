package org.lilachshop.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
@Transactional
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    private Long id;

    public Order(String creationDate, String greetingCard, List<Item> items, int totalPrice, int amountOfProducts, DeliveryDetails deliveryDetails, PickUpDetails pickUpDetails, Complaint complaint, Customer customer) {
        this.creationDate = creationDate;
        this.greetingCard = greetingCard;
        this.items = items;
        this.totalPrice = totalPrice;
        this.amountOfProducts = amountOfProducts;
        this.deliveryDetails = deliveryDetails;
        this.pickUpDetails = pickUpDetails;
        this.complaint = complaint;
        this.customer = customer;
    }

    String creationDate;
    String greetingCard;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Item> items;
    int totalPrice;
    int amountOfProducts;
    @OneToOne(cascade = CascadeType.ALL)
    DeliveryDetails deliveryDetails;
    @OneToOne(cascade = CascadeType.ALL)
    PickUpDetails pickUpDetails;

    @OneToOne(cascade = CascadeType.ALL)
    Complaint complaint;

    @ManyToOne
    Customer customer;

    protected Order() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
