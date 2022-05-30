package org.lilachshop.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;

@Transactional
@Embeddable
public class Account implements Serializable {


    String creationDate;
    AccountType accountType;

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
