package com.example.onelabpractice1.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Comparator;

@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String number;
    private double balance;

    public Card(String number, double balance) {
        this.number = number;
        this.balance = balance;
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
