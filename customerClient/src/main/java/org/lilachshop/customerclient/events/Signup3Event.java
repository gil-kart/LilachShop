package org.lilachshop.customerclient.events;

import org.lilachshop.entities.AccountType;
import org.lilachshop.entities.Store;


//Event to pass from SignUp3 to final stage to create new costumer
public class Signup3Event {
        AccountType chosenAccount;

    Store chosenStore;

    public Signup3Event(Store store) {
        this.chosenAccount = AccountType.STORE_ACCOUNT;
        this.chosenStore = store;
    }


    public Signup3Event(AccountType chosenAccount) {
        this.chosenAccount = chosenAccount;
        //TODO:SET STORE TO BE THE DEFAULT STORE(CHAIN)
    }

    public AccountType getChosenAccount() {
        return chosenAccount;
    }

    public Store getStore() {
       return chosenStore;
   }
}
