package com.example.onelabpractice1.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
public class Card {
    private String number;
    private double balance;

    public Card() {

    }

    public Card(String number) {
        this.number = number;
        this.balance = 500;
    }

    public static Comparator<Card> COMPARE_BY_BALANCE = Comparator.comparing(Card::getBalance);

    @Override
    public String toString() {
        return "\n" + "Card{ " +
                "number: " + number +
                ", balance: " + balance +
                " }";
    }
}
