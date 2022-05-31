package org.lilachshop.customerclient.events;

import org.lilachshop.entities.AccountType;
import org.lilachshop.entities.Store;


//Event to pass from SignUp3 to final stage to create new costumer
public class Signup3Event {
    AccountType chosenAccount;
    Store chosenStore;


    public Signup3Event(AccountType chosenAccount, Store store) {
        this.chosenAccount = chosenAccount;
        this.chosenStore = store;
    }

    public Signup3Event() {
    }

    public AccountType getChosenAccount() {
        return chosenAccount;
    }

    public Store getStore() {
        return chosenStore;
    }
}
