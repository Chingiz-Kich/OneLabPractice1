package com.example.onelabpractice1.service;

import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    private CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    public List<Card> getAllByBalanceASC() {
        List<Card> cards = getAll();
        cards.sort(Card.COMPARE_BY_BALANCE);
        return cards;
    }

    public Card getCardByNumber(String number) {
        return cardRepository.getCardByNumber(number);
    }

    public void deposit(String cardNumber, double money) {
        Card card = cardRepository.getCardByNumber(cardNumber);
        card.setBalance(card.getBalance() + money);
        cardRepository.save(card);
    }

    public void withdraw(String cardNumber, double money) {
        Card card = cardRepository.getCardByNumber(cardNumber);
        card.setBalance(card.getBalance() - money);
        cardRepository.save(card);
    }
}
