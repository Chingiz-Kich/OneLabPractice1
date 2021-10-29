package com.example.onelabpractice1.models;

import com.example.onelabpractice1.helper.CardHelper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Comparator;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;
    private String surname;

    @Column(unique = true)
    private String phoneNumber;

    @OneToOne(targetEntity = Card.class,
            cascade = CascadeType.ALL)
    private Card card;

    {
        this.card = createCard();
    }

    public User(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
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
