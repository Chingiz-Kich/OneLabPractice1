package com.example.onelabpractice1.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TransferByPhoneRequest {
    @NotNull(message = "Phone number cannot be null")
    @NotEmpty(message = "Phone number cannot be empty")
    private String senderPhoneNumber;

    @NotNull(message = "Phone number cannot be null")
    @NotEmpty(message = "Phone number cannot be empty")
    private String recipientPhoneNumber;

    @NotNull(message = "Money field cannot be null")
    @NotEmpty(message = "Money field cannot be empty")
    private double money;
}
