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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

import static org.mockito.Mockito.*;

class TransferControllerTest {
    @Mock
    UserService userService;
    @Mock
    CardService cardService;
    @Mock
    TransferService transferService;
    @InjectMocks
    TransferController sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private static Stream<Arguments> checking() {
        return Stream.of(
                Arguments.of(true, true),
                Arguments.of(true, false),
                Arguments.of(false, true)
        );
    }

    @ParameterizedTest
    @MethodSource("checking")
    void testTransferByPhone(boolean existPhoneNumber, boolean enoughBalance) {
        TransferByPhoneRequest transferByPhoneRequest = Prototype.transferByPhoneRequest();
        User user1 = Prototype.userA();
        User user2 = Prototype.userB();

        when(userService.isPhoneNumberExist(transferByPhoneRequest.getSenderPhoneNumber())).thenReturn(existPhoneNumber);
        when(userService.getByPhoneNumber(transferByPhoneRequest.getSenderPhoneNumber())).thenReturn(user1);
        when(cardService.isEnoughBalance(transferByPhoneRequest.getSenderPhoneNumber(), transferByPhoneRequest.getMoney())).thenReturn(enoughBalance);

        when(userService.isPhoneNumberExist(transferByPhoneRequest.getRecipientPhoneNumber())).thenReturn(existPhoneNumber);
        when(userService.getByPhoneNumber(transferByPhoneRequest.getRecipientPhoneNumber())).thenReturn(user2);
        when(cardService.isEnoughBalance(transferByPhoneRequest.getRecipientPhoneNumber(), transferByPhoneRequest.getMoney())).thenReturn(enoughBalance);

        ResponseEntity<?> result = sut.transferByPhone(transferByPhoneRequest);

        if (!existPhoneNumber) {
            Assertions.assertEquals(ResponseEntity.ok(Response.PHONE_NUMBER_NOT_FOUND), result);
        } else if (!enoughBalance) {
            Assertions.assertEquals(ResponseEntity.ok(Response.BALANCE_IS_NOT_ENOUGH), result);
        } else {
            Assertions.assertEquals(ResponseEntity.ok(Response.OK), result);
        }
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme