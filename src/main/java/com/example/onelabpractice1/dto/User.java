package com.example.onelabpractice1.dto;

import com.example.onelabpractice1.helper.CardHelper;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
public class User {
    private String name;
    private String surname;
    private String phoneNumber;
    private Card card;

    public User(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.card = createCard();
    }

    public static Comparator<User> COMPARE_BY_NAME = Comparator.comparing(User::getName);

    @Override
    public String toString() {
        return "\n"+ "User{ " +
                "Name: " + name + "\n" +
                "Surname " + surname + "\n" +
                "Phone number: " + phoneNumber + "\n" +
                "Card { "  +
                "number:" + card.getNumber() +
                " balance" + card.getBalance() +
                " }" + "}";
    }

    private Card createCard() {
        String cardNumber = CardHelper.getNumber();
        return new Card(cardNumber);
    }
}
