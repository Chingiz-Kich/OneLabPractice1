package com.example.onelabpractice1.repository;

import com.example.onelabpractice1.dto.Card;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CardRepo implements ICardRepo {
    Map<String, Card> cards = new HashMap<>();

    @Override
    public Card getCardByNumber(String number) {
        return cards.get(number);
    }

    @Override
    public ArrayList getAll() {
        return new ArrayList(cards.values());
    }

    @Override
    public void saveCard(Card card) {
        cards.put(card.getNumber(), card);
    }

    @Override
    public boolean isCardExist(Card card) {
        return cards.containsKey(card.getNumber());
    }
}
