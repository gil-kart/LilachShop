package org.lilachshop.entities;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;

@Transactional
@Entity
@Table(name = "DeliveryDetails")
public class DeliveryDetails implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    Order order;

    LocalDate DeliveryTime;

    public DeliveryDetails(LocalDate deliveryTime, String phoneNumber, String receiverName, String address) {
        DeliveryTime = deliveryTime;
        this.phoneNumber = phoneNumber;
        this.receiverName = receiverName;
        this.address = address;
    }

    public LocalDate getDeliveryTime() {
        return DeliveryTime;
    }

    public void setDeliveryTime(LocalDate deliveryTime) {
        DeliveryTime = deliveryTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getAddress() {
        return address;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    protected DeliveryDetails() {
    }

    String phoneNumber;
    String receiverName;
    String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
