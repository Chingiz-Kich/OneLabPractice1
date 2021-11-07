package com.example.onelabpractice1.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DepositRequest {

    @NotNull(message = "Phone number cannot be null")
    @NotEmpty(message = "Phone number cannot be empty")
    private String phoneNumber;

    @NotNull(message = "Money field cannot be null")
    private double money;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getMoney() {
        return money;
    }
}
