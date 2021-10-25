package com.example.onelabpractice1.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Transfer {
    private String senderPhoneNumber;
    private String recipientPhoneNumber;
    private double money;
    private Date transferDate;

    public Transfer() {

    }

    public Transfer(String senderPhoneNumber, String recipientPhoneNumber, double money, Date transferDate) {
        this.senderPhoneNumber = senderPhoneNumber;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.money = money;
        this.transferDate = transferDate;
    }

    @Override
    public String toString() {
        return "\n" + "Sender: " + senderPhoneNumber + "\n" +
                "Recipient: " + recipientPhoneNumber + "\n" +
                "Money: " + money + "\n" +
                "Date: " + transferDate.toString();
    }
}
