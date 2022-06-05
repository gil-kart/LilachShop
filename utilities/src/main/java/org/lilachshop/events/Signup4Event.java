package org.lilachshop.events;


import org.lilachshop.entities.CreditCard;

import java.time.LocalDate;
import java.time.YearMonth;

//Event to pass from SignUp4 to final stage to create new costumer
public class Signup4Event{
  CreditCard card;

    public CreditCard getCard() {
        return card;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

    public Signup4Event(CreditCard card) {
      this.card =card;

    }
 }

