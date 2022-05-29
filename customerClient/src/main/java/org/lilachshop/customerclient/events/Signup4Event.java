package org.lilachshop.customerclient.events;


//Event to pass from SignUp4 to final stage to create new costumer
public class Signup4Event{
    String cardNumber;
    String expDate;
    String cardOwnerName;
    String cardOwnerID;

    public Signup4Event(String cardNumber, String expDate, String cardOwnerName, String cardOwnerID) {
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.cardOwnerName = cardOwnerName;
        this.cardOwnerID = cardOwnerID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getCardOwnerName() {
        return cardOwnerName;
    }

    public String getCardOwnerID() {
        return cardOwnerID;
    }
}

