package com.example.onelabpractice1.service;

import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.repository.CardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class CardServiceTest {
    @Mock
    CardRepository cardRepository;
    @InjectMocks
    CardService sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAll() {
        when(cardRepository.findAll()).thenReturn(Arrays.<Card>asList(new Card("number")));

        List<Card> result = sut.getAll();
        Assertions.assertEquals(Arrays.<Card>asList(new Card("number")), result);
    }

    @Test
    void testGetAllByBalanceASC() {
        when(cardRepository.findAll()).thenReturn(Arrays.<Card>asList(new Card("number")));

        List<Card> result = sut.getAllByBalanceASC();
        Assertions.assertEquals(Arrays.<Card>asList(new Card("number")), result);
    }

    @Test
    void testGetCardByNumber() {
        when(cardRepository.getCardByNumber(anyString())).thenReturn(new Card("number"));

        Card result = sut.getCardByNumber("number");
        Assertions.assertEquals(new Card("number"), result);
    }

    @Test
    void testDeposit() {
        when(cardRepository.getCardByNumber(anyString())).thenReturn(new Card("number"));

        sut.deposit("cardNumber", 0d);
    }

    @Test
    void testWithdraw() {
        when(cardRepository.getCardByNumber(anyString())).thenReturn(new Card("number"));

        sut.withdraw("cardNumber", 0d);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme