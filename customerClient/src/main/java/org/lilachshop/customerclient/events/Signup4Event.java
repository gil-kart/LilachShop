package org.lilachshop.customerclient.events;


import java.time.LocalDate;

//Event to pass from SignUp4 to final stage to create new costumer
public class Signup4Event{
    String cardNumber;
    LocalDate expDate;
    String cardOwnerName;
    int cardOwnerID;

    public Signup4Event(String cardNumber, LocalDate expDate, String cardOwnerName, int cardOwnerID) {
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.cardOwnerName = cardOwnerName;
        this.cardOwnerID = cardOwnerID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public String getCardOwnerName() {
        return cardOwnerName;
    }

    public int getCardOwnerID() {
        return cardOwnerID;
    }
}

