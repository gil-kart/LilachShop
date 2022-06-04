package org.lilachshop.events;


import java.time.LocalDate;
import java.time.YearMonth;

//Event to pass from SignUp4 to final stage to create new costumer
public class Signup4Event{
    String cardNumber;
    YearMonth expDate;
    String cardOwnerName;
    int cardOwnerID;

    public Signup4Event(String cardNumber, YearMonth expDate, String cardOwnerName, int cardOwnerID) {
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.cardOwnerName = cardOwnerName;
        this.cardOwnerID = cardOwnerID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public YearMonth getExpDate() {
        return expDate;
    }

    public String getCardOwnerName() {
        return cardOwnerName;
    }

    public int getCardOwnerID() {
        return cardOwnerID;
    }
}

