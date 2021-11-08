package com.example.onelabpractice1.service;

import com.example.onelabpractice1.Prototype;
import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.repository.CardRepository;
import com.example.onelabpractice1.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class CardServiceTest {
    @Mock
    CardRepository cardRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    CardService cardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateCard() {
        Card result = cardService.createCard();

        when(cardRepository.save(result)).thenReturn(result);
        when(cardRepository.findCardByNumber(result.getNumber())).thenReturn(result);

        Card expectedResult = cardRepository.findCardByNumber(result.getNumber());
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void testGetAll() {
        Card card1 = Prototype.createCard1();
        Card card2 = Prototype.createCard2();

        List<Card> cardList = new ArrayList<>();
        cardList.add(card1);
        cardList.add(card2);

        when(cardRepository.findAll()).thenReturn(cardList);

        List<Card> result = cardService.getAll();
        Assertions.assertEquals(cardList, result);
    }

    @Test
    void testGetAllByBalanceASC() {
        Card card1 = Prototype.createCard1();
        Card card2 = Prototype.createCard2();

        List<Card> cardList = new ArrayList<>();
        cardList.add(card1);
        cardList.add(card2);

        when(cardRepository.findAll()).thenReturn(cardList);

        cardList.sort(Card.COMPARE_BY_BALANCE);

        List<Card> result = cardService.getAllByBalanceASC();
        Assertions.assertEquals(cardList, result);
    }

    @Test
    void testGetCardByCardNumber() {
        Card card1 = Prototype.createCard1();
        String cardNumber = card1.getNumber();

        when(cardRepository.findCardByNumber(cardNumber)).thenReturn(card1);

        Card result = cardService.getCardByCardNumber(cardNumber);

        Assertions.assertEquals(card1, result);
    }

    @Test
    void testDepositByPhoneNumber() {
        User user1 = Prototype.userAaa();
        double expectedBalance = user1.getCard().getBalance() + 500;

        when(userRepository.findByPhoneNumber(user1.getPhoneNumber())).thenReturn(user1);

        cardService.depositByPhoneNumber(user1.getPhoneNumber(), 500);
        double result = user1.getCard().getBalance();

        Assertions.assertEquals(expectedBalance, result);
    }

    @Test
    void testWithdrawByPhoneNumber() {
        User user1 = Prototype.userAaa();
        double expectedBalance = user1.getCard().getBalance() - 500;

        when(userRepository.findByPhoneNumber(user1.getPhoneNumber())).thenReturn(user1);

        cardService.withdrawByPhoneNumber(user1.getPhoneNumber(), 500);
        double result = user1.getCard().getBalance();

        Assertions.assertEquals(expectedBalance, result);
    }

    @Test
    void testIsEnoughBalance() {
        User user1 = Prototype.userAaa();

        when(userRepository.getByPhoneNumber(user1.getPhoneNumber())).thenReturn(user1);

        boolean result = cardService.isEnoughBalance(user1.getPhoneNumber(), 500);
        Assertions.assertTrue(result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme