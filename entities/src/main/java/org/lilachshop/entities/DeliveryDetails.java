package org.lilachshop.entities;


import javax.persistence.*;
import javax.swing.text.Utilities;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    LocalDateTime DeliveryTime;

    public DeliveryDetails(LocalDateTime deliveryTime, String phoneNumber, String receiverName, String address) {
        DeliveryTime = deliveryTime;
        this.phoneNumber = phoneNumber;
        this.receiverName = receiverName;
        this.address = address;
    }

    public LocalDateTime getDeliveryTime() {
        return DeliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setAddress(String address) {
        this.address = address;
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
