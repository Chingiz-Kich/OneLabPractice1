package com.example.onelabpractice1.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Transfer {
    private User sender;
    private User recipient;
    private double money;
    private LocalDate transferDate;

    public Transfer(User sender, User recipient, double money, LocalDate transferDate) {
        this.sender = sender;
        this.recipient = recipient;
        this.money = money;
        this.transferDate = transferDate;
    }

    @Override
    public String toString() {
        return "\n" + "Sender: " + sender + "\n" +
                "Recipient: " + recipient + "\n" +
                "Money: " + money + "\n" +
                "Date: " + transferDate.toString();
    }
}
