package org.lilachshop.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Entity
public class Customer extends User implements Serializable {

    public Customer(String userName, String userPassword, String name, String address, String phoneNumber, CreditCard card, List<Order> orders, Store store, Account account, ActiveDisabledState... disabled) {
        super(userName, userPassword);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.card = card;
        this.orders = orders;
        this.store = store;
        this.account = account;
        this.disabled = disabled.length > 0 ? disabled[0] : ActiveDisabledState.ACTIVE;
    }

    @Embedded
    Account account;
    String name;
    String address;
    String phoneNumber;

    @Enumerated(EnumType.STRING)
    ActiveDisabledState disabled;


    @Embedded
    CreditCard card;

    @OneToMany
    List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "store_id")
    Store store;

    public List<Order> getOrders() {
        return orders;
    }

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

    public void setPhoneNumber(String phoneNumber) throws Exception {
        if (phoneNumber == null)
            return;
        Pattern pattern = Pattern.compile("^[0-9]\\d*$");
        int length = phoneNumber.length();
        if (pattern.matcher(phoneNumber).matches() &&
                ((length == 10 && phoneNumber.startsWith("05")) || (length == 9 && phoneNumber.startsWith("0"))))
            this.phoneNumber = phoneNumber;
        else
            throw new Exception("מספר טלפון אינו תקין");
    }

    public ActiveDisabledState getAccountState() {
        return disabled;
    }

    public void setDisabled(ActiveDisabledState disabled) {
        this.disabled = disabled;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

    public AccountType getAccountType() {
        return account.accountType;
    }

    public LocalDate getCreationDate() {
        return account.creationDate;
    }

    public String getCardExpDate() {
        return card.getExpDateStringFormat();
    }

    public String getCardThreeDigits() {
        return card.getThreeDigits();
    }

    public String getCardOwnerName() {
        return card.getOwnerName();
    }

    public String getCardOwnerId() {
        return card.getCardOwnerId();
    }

    public String getCardNumber() {
        return card.getNumber();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
