package com.example.onelabpractice1.controllers;

import com.example.onelabpractice1.Prototype;
import com.example.onelabpractice1.enums.Response;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.requests.TransferByPhoneRequest;
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
        User user1 = Prototype.userA();
        User user2 = Prototype.userB();
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        when(userService.getAllUsers()).thenReturn(userList);

        List<User> result = adminController.getAllUsers();
        Assertions.assertEquals(userList, result);
    }

    @Test
    void testGetAllUsersSortByName() {
        User user1 = Prototype.userA();
        User user2 = Prototype.userB();
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        when(userService.getAllSortByName()).thenReturn(userList);

        List<User> result = adminController.getAllUsersSortByName();
        Assertions.assertEquals(userList, result);
    }

    @Test
    void testGetAllUserWithName() {
        User user1 = Prototype.userA();
        List<User> userList = new ArrayList<>();
        userList.add(user1);

        when(userService.getAllWithName("Aaa")).thenReturn(userList);

        List<User> result = adminController.getAllUserWithName("Aaa");
        Assertions.assertEquals(userList, result);
    }

    @Test
    void testTransferByPhone() {
        TransferByPhoneRequest transferByPhoneRequest = Prototype.transferByPhoneRequest();
        User user1 = Prototype.userA();
        User user2 = Prototype.userB();

        when(userService.isPhoneNumberExist(transferByPhoneRequest.getSenderPhoneNumber())).thenReturn(true);
        when(userService.getByPhoneNumber(transferByPhoneRequest.getSenderPhoneNumber())).thenReturn(user1);
        when(cardService.isEnoughBalance(transferByPhoneRequest.getSenderPhoneNumber(), transferByPhoneRequest.getMoney())).thenReturn(true);

        when(userService.isPhoneNumberExist(transferByPhoneRequest.getRecipientPhoneNumber())).thenReturn(true);
        when(userService.getByPhoneNumber(transferByPhoneRequest.getRecipientPhoneNumber())).thenReturn(user2);
        when(cardService.isEnoughBalance(transferByPhoneRequest.getRecipientPhoneNumber(), transferByPhoneRequest.getMoney())).thenReturn(true);

        ResponseEntity<?> result = adminController.transferByPhone(transferByPhoneRequest);
        Assertions.assertEquals(ResponseEntity.ok(Response.OK), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme