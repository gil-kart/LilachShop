package org.lilachshop.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;

@Transactional
@Entity
@Table(name = "CreditCard")
public class CreditCard implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    String number;
    String expDate;
    String threeDigits;
//    String ownerName;
//    String ownerID;

    @OneToOne
    Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
