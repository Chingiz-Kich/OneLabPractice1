package com.example.onelabpractice1.service;

import com.example.onelabpractice1.Prototype;
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
        User user1 = Prototype.userA();
        User user2 = Prototype.userB();

        sut.makeTransfer(user1, user2, 500);
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

        User user1 = Prototype.userA();
        User user2 = Prototype.userB();

        List<Transfer> result = sut.getTransferHistorySenderRecipient(user1, user2);

        Assertions.assertEquals(transferList, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme