package com.example.onelabpractice1.dao;

import com.example.onelabpractice1.models.Card;

import java.util.List;

public interface CardDao {
    List<Card> getAll();
    Card getByCardNumber(String number);
    void saveCard(Card card);
    Card isCardExist(Card card);
    void updateBalance(Card card);
}
