package org.lilachshop.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class CreditCard implements Serializable {

    String number;
    LocalDate expDate;
    String threeDigits;
    String ownerName;

    String cardOwnerId;

    @OneToOne
    Customer customer;

    protected CreditCard() {
    }


    public String getNumber() {
        return number;
    }

    public CreditCard(String number, LocalDate expDate, String ownerName, String cardOwnerID, String threeDigits) {
        this.number = number;
        this.expDate = expDate;
        this.ownerName = ownerName;
        this.cardOwnerId = cardOwnerID;
        this.threeDigits = threeDigits;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public String getThreeDigits() {
        return threeDigits;
    }



    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
