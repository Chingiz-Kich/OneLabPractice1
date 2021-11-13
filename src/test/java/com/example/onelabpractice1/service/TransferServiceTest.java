package com.example.onelabpractice1.service;

import com.example.onelabpractice1.Prototype;
import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.models.Transfer;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.repository.CardRepository;
import com.example.onelabpractice1.repository.TransferRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;

class TransferServiceTest {
    @Mock
    TransferRepository transferRepository;
    @Mock
    CardRepository cardRepository;
    @InjectMocks
    TransferService sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testMakeTransfer() {
        User user1 = mock(User.class);
        Card card1 = mock(Card.class);
        Transfer transfer1 = Prototype.transfer1();

        when(card1.getNumber()).thenReturn("cardNumber");
        when(card1.getBalance()).thenReturn(1000d);
        when(user1.getCard()).thenReturn(card1);
        when(user1.getCard().getBalance()).thenReturn(1000d);
        when(transferRepository.save(transfer1)).thenReturn(transfer1);

        sut.makeTransfer(user1, user1, 1000);
        Assertions.assertTrue(true);
    }

    @Test
    void testGetAllTransfersByDate() {
        Transfer transfer1 = Prototype.transfer1();
        Transfer transfer2 = Prototype.transfer2();
        List<Transfer> transferList = new ArrayList<>();
        transferList.add(transfer1);
        transferList.add(transfer2);

        when(transferRepository.findAll()).thenReturn(transferList);

        List<Transfer> result = sut.getAllTransfersByDate();
        transferList.sort(Transfer.COMPARE_BY_DATE);
        Assertions.assertEquals(transferList, result);
    }

    @Test
    void testGetTransferHistorySenderRecipient() {
        Transfer transfer1 = Prototype.transfer1();
        List<Transfer> transferList = new ArrayList<>();
        transferList.add(transfer1);

        when(transferRepository.findAll()).thenReturn(transferList);


        List<Transfer> result = sut.getTransferHistorySenderRecipient("phoneNumber1", "phoneNumber2");

        Assertions.assertEquals(transferList, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme