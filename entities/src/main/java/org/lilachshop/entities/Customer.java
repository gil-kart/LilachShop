package org.lilachshop.entities;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Customers")
public class Customer extends User implements Serializable {

    public Customer(String userName, String userPassword, String name, String address, String phoneNumber, Boolean disabled, CreditCard card, List<Order> orders, Store store, Account account) {
        super(userName, userPassword);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.disabled = disabled;
        this.card = card;
        this.orders = orders;
        this.store = store;
        this.account = account;
    }

    @Embedded
    Account account;
    String name;
    String address;
    String phoneNumber;
    Boolean disabled;


    @Embedded
    CreditCard card;

    @OneToMany
    List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "store_id")
    Store store;


    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Customer() {
    }

    public void addOrderToList(Order order) {
        this.orders.add(order);
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
