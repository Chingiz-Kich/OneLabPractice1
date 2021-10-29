package com.example.onelabpractice1.service;

import com.example.onelabpractice1.models.Transfer;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.repository.CardRepository;
import com.example.onelabpractice1.repository.TransferRepository;
import com.example.onelabpractice1.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.Mockito.*;

class TransferServiceTest {
    @Mock
    TransferRepository transferRepository;
    @Mock
    UserRepository userRepository;
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
        when(userRepository.existsUserByPhoneNumber(anyString())).thenReturn(true);

        sut.makeTransfer(new User("name", "surname", "phoneNumber"), new User("name", "surname", "phoneNumber"), 0d);
    }

    @Test
    void testGetAllTransfersByDate() {
        when(transferRepository.findAll()).thenReturn(Arrays.<Transfer>asList(new Transfer("senderPhoneNumber", "recipientPhoneNumber", 0d, new GregorianCalendar(2021, Calendar.OCTOBER, 29, 22, 31).getTime())));

        List<Transfer> result = sut.getAllTransfersByDate();
        Assertions.assertEquals(Arrays.<Transfer>asList(new Transfer("senderPhoneNumber", "recipientPhoneNumber", 0d, new GregorianCalendar(2021, Calendar.OCTOBER, 29, 22, 31).getTime())), result);
    }

    @Test
    void testGetTransferHistorySenderRecipient() {
        when(transferRepository.findAll()).thenReturn(Arrays.<Transfer>asList(new Transfer("senderPhoneNumber", "recipientPhoneNumber", 0d, null)));

        List<Transfer> result = sut.getTransferHistorySenderRecipient(new User(null, null, "phoneNumber"), new User(null, null, "phoneNumber"));
        Assertions.assertEquals(Arrays.<Transfer>asList(new Transfer("senderPhoneNumber", "recipientPhoneNumber", 0d, null)), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme