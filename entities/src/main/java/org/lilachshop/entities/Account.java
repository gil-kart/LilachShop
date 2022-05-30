package org.lilachshop.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;

@Transactional
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    String creationDate;
    AccountType accountType;
    public Long getId() {
        return id;
    }

    protected Account() {}

    public AccountType getAccountType() {
        return accountType;
    }


    public  Account(AccountType accountType){
        this.creationDate = LocalDate.now().toString();
        this.accountType = accountType;
    }

    public Account(String creationDate, AccountType accountType) {
        this.creationDate = creationDate;
        this.accountType = accountType;
    }
}
