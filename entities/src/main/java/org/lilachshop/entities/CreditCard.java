package org.lilachshop.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
@Embeddable
public class CreditCard implements Serializable {

    String number;
    String expDate;
    String threeDigits;
//    String ownerName;
//    String ownerID;

    @OneToOne
    Customer customer;

    protected CreditCard() {
    }


    public String getNumber() {
        return number;
    }

    public CreditCard(String number, String expDate) {
        this.number = number;
        this.expDate = expDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getThreeDigits() {
        return threeDigits;
    }

    public CreditCard(String number, String expDate, String threeDigits) {
        this.number = number;
        this.expDate = expDate;
        this.threeDigits = threeDigits;
    }
    public void setCustomer(Customer customer){this.customer = customer;}
}
