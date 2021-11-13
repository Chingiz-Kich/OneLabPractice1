package com.example.onelabpractice1.controllers;

import com.example.onelabpractice1.Prototype;
import com.example.onelabpractice1.enums.Response;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.requests.UpdateRequest;
import com.example.onelabpractice1.service.CardService;
import com.example.onelabpractice1.service.TransferService;
import com.example.onelabpractice1.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
    AdminController sut;

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

        List<User> result = sut.getAllUsers();
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

        List<User> result = sut.getAllUsersSortByName();
        Assertions.assertEquals(userList, result);
    }

    @Test
    void testGetAllUserWithName() {
        User user1 = Prototype.userA();
        List<User> userList = new ArrayList<>();
        userList.add(user1);

        when(userService.getAllWithName("Aaa")).thenReturn(userList);

        List<User> result = sut.getAllUserWithName("Aaa");
        Assertions.assertEquals(userList, result);
    }

    @Test
    void testUpdateUser() {
        UpdateRequest updateRequest = mock(UpdateRequest.class);

        when(userService.isPhoneNumberExist(updateRequest.getPhoneNumber())).thenReturn(true);

        sut.update(updateRequest);
        verify(userService, times(1)).updateUser(updateRequest);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testDeleteUser(boolean isPhoneNumberExist) {
        User user = mock(User.class);

        when(userService.isPhoneNumberExist("phoneNumber")).thenReturn(isPhoneNumberExist);
        when(userService.getByPhoneNumber("phoneNumber")).thenReturn(user);

        ResponseEntity<?> result = sut.delete("phoneNumber");
        if (isPhoneNumberExist) {
            Assertions.assertEquals(ResponseEntity.ok(Response.DELETED_SUCCESSFULLY), result);
        } else {
            Assertions.assertEquals(ResponseEntity.ok(Response.PHONE_NUMBER_NOT_FOUND), result);
        }
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme