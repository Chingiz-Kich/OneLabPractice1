package com.example.onelabpractice1.models;

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
    private String email;
    private String phoneNumber;
    private String password;

    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToOne(targetEntity = Card.class)
    private Card card;

    public User(String name, String surname, String email, String phoneNumber, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
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
}
