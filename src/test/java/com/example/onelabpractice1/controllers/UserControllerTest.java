package com.example.onelabpractice1.controllers;

import com.example.onelabpractice1.Prototype;
import com.example.onelabpractice1.constants.Constants;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.requests.DepositRequest;
import com.example.onelabpractice1.requests.UserRequest;
import com.example.onelabpractice1.service.CardService;
import com.example.onelabpractice1.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

class UserControllerTest {
    @Mock
    UserService userService;
    @Mock
    CardService cardService;
    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDeposit() {
        UserRequest userRequest1 = Prototype.userRequestAaa();

        when(userService.registration(userRequest1, false)).thenReturn(true);
        when(userService.isPhoneNumberExist("phoneNumber1")).thenReturn(true);

        DepositRequest depositRequest1 = Prototype.depositRequestAaa();

        ResponseEntity<?> result = userController.deposit(depositRequest1);
        Assertions.assertEquals(ResponseEntity.ok(Constants.OK), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme