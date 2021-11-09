package com.example.onelabpractice1.controllers;

import com.example.onelabpractice1.Prototype;
import com.example.onelabpractice1.constants.Constants;
import com.example.onelabpractice1.requests.DepositRequest;
import com.example.onelabpractice1.requests.UserRequest;
import com.example.onelabpractice1.requests.WithdrawRequest;
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
        DepositRequest depositRequest1 = Prototype.depositRequestA();

        when(userService.isPhoneNumberExist(depositRequest1.getPhoneNumber())).thenReturn(true);

        ResponseEntity<?> result = userController.deposit(depositRequest1);
        Assertions.assertEquals(ResponseEntity.ok(Constants.OK), result);
    }

    @Test
    void testDepositFail() {
        DepositRequest depositRequest1 = Prototype.depositRequestA();

        when(userService.isPhoneNumberExist(depositRequest1.getPhoneNumber())).thenReturn(false);

        ResponseEntity<?> result = userController.deposit(depositRequest1);
        Assertions.assertEquals(ResponseEntity.ok(Constants.PHONE_NUMBER_NOT_FOUND), result);
    }

    @Test
    void testWithdraw() {
        WithdrawRequest withdrawRequest1 = Prototype.withdrawRequestA();

        when(userService.isPhoneNumberExist(withdrawRequest1.getPhoneNumber())).thenReturn(true);
        when(cardService.isEnoughBalance(withdrawRequest1.getPhoneNumber(), withdrawRequest1.getMoney())).thenReturn(true);

        ResponseEntity<?> result = userController.withdraw(withdrawRequest1);
        Assertions.assertEquals(ResponseEntity.ok(Constants.OK), result);
    }

    @Test
    void testWithdrawFail1() {
        WithdrawRequest withdrawRequest1 = Prototype.withdrawRequestA();

        when(userService.isPhoneNumberExist(withdrawRequest1.getPhoneNumber())).thenReturn(false);

        ResponseEntity<?> result = userController.withdraw(withdrawRequest1);
        Assertions.assertEquals(ResponseEntity.ok(Constants.PHONE_NUMBER_NOT_FOUND), result);
    }

    @Test
    void testWithdrawFail2() {
        WithdrawRequest withdrawRequest1 = Prototype.withdrawRequestA();

        when(userService.isPhoneNumberExist(withdrawRequest1.getPhoneNumber())).thenReturn(true);
        when(cardService.isEnoughBalance(withdrawRequest1.getPhoneNumber(), withdrawRequest1.getMoney())).thenReturn(false);

        ResponseEntity<?> result = userController.withdraw(withdrawRequest1);
        Assertions.assertEquals(ResponseEntity.ok(Constants.BALANCE_IS_NOT_ENOUGH), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme