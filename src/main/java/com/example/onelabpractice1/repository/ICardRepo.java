package com.example.onelabpractice1.repository;

import com.example.onelabpractice1.dto.Card;

import java.util.List;

public interface ICardRepo {
    Card getCardByNumber(String number);

    List<Card> getAll();

    void saveCard(Card card);

    boolean isCardExist(Card card);
}
