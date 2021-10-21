package com.example.onelabpractice1.service;

import com.example.onelabpractice1.dto.Card;
import com.example.onelabpractice1.repository.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    private CardRepo cardRepository;

    @Autowired
    public CardService(CardRepo cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getAll() {
        return cardRepository.getAll();
    }

    public List<Card> getAllByBalanceASC() {
        List<Card> cards = getAll();
        cards.sort(Card.COMPARE_BY_BALANCE);
        return cards;
    }

    public Card getCardByNumber(String number) {
        return cardRepository.getCardByNumber(number);
    }
}
