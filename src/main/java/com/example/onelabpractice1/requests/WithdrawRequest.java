package com.example.onelabpractice1.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WithdrawRequest {
    @NotNull(message = "Phone number cannot be null")
    @NotEmpty(message = "Phone number cannot be empty")
    private String phoneNumber;

    @NotNull(message = "Money field cannot be null")
    private double money;
}
