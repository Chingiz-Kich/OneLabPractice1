package com.example.onelabpractice1.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Date;

@Entity
@Table(name = "transfers")
@Getter
@Setter
@NoArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String senderPhoneNumber;
    private String recipientPhoneNumber;
    private double money;
    private Date transferDate;


    public Transfer(String senderPhoneNumber, String recipientPhoneNumber, double money, Date transferDate) {
        this.senderPhoneNumber = senderPhoneNumber;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.money = money;
        this.transferDate = transferDate;
    }

    public static final Comparator<Transfer> COMPARE_BY_DATE = Comparator.comparing(Transfer::getTransferDate);

    @Override
    public String toString() {
        return "\n" + "Sender: " + senderPhoneNumber + "\n" +
                "Recipient: " + recipientPhoneNumber + "\n" +
                "Money: " + money + "\n" +
                "Date: " + transferDate.toString();
    }
}
