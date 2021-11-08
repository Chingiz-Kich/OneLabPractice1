package com.example.onelabpractice1.controllers;

import com.example.onelabpractice1.Prototype;
import com.example.onelabpractice1.constants.Constants;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.requests.TransferByPhoneRequest;
import com.example.onelabpractice1.requests.UserRequest;
import com.example.onelabpractice1.service.CardService;
import com.example.onelabpractice1.service.TransferService;
import com.example.onelabpractice1.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class AdminControllerTest {
    @Mock
    UserService userService;
    @Mock
    CardService cardService;
    @Mock
    TransferService transferService;
    @InjectMocks
    AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUsers() {
        User user1 = Prototype.userAaa();
        User user2 = Prototype.userName();
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        when(userService.getAllUsers()).thenReturn(userList);

        List<User> result = adminController.getAllUsers();
        Assertions.assertEquals(userList, result);
    }

    @Test
    void testTransferByPhone() {
        TransferByPhoneRequest transferByPhoneRequest = Prototype.transferByPhoneRequest();
        User user1 = Prototype.userAaa();
        User user2 = Prototype.userName();

        UserRequest userRequest1 = Prototype.userRequestAaa();
        UserRequest userRequest2 = Prototype.userRequestName();
        userService.registration(userRequest1, false);
        userService.registration(userRequest2, false);

        when(userService.getByPhoneNumber("phoneNumber1")).thenReturn(user1);
        when(userService.isPhoneNumberExist("phoneNumber1")).thenReturn(true);
        when(cardService.isEnoughBalance("phoneNumber1", 300)).thenReturn(true);

        when(userService.getByPhoneNumber("phoneNumber2")).thenReturn(user2);
        when(userService.isPhoneNumberExist("phoneNumber2")).thenReturn(true);
        when(cardService.isEnoughBalance("phoneNumber2", 300)).thenReturn(true);
        transferService.makeTransfer(user1, user2, transferByPhoneRequest.getMoney());

        ResponseEntity<?> result = adminController.transferByPhone(transferByPhoneRequest);
        Assertions.assertEquals(ResponseEntity.ok(Constants.OK), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme