package com.example.onelabpractice1.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateRequest {
    @NotNull(message = "Phone number cannot be null")
    @NotEmpty(message = "Phone number cannot be empty")
    private String phoneNumber;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Surname cannot be null")
    private String surname;

    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password cannot be null")
    private String password;

}
