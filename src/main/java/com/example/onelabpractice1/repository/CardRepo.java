package com.example.onelabpractice1.repository;

import com.example.onelabpractice1.dao.Impl.CardDaoImpl;
import com.example.onelabpractice1.models.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CardRepo implements ICardRepo {

    private CardDaoImpl cardDaoImpl;

    @Autowired
    public CardRepo(CardDaoImpl cardDaoImpl) {
        this.cardDaoImpl = cardDaoImpl;
    }

    @Override
    public Card getCardByNumber(String number) {
        return cardDaoImpl.getByCardNumber(number);
    }

    @Override
    public ArrayList getAll() {
        return new ArrayList(cardDaoImpl.getAll());
    }

    @Override
    public void saveCard(Card card) {
        cardDaoImpl.saveCard(card);
    }

    @Override
    public boolean isCardExist(Card card) {
        return cardDaoImpl.isCardExist(card) != null;
    }

    @Override
    public void updateBalance(Card card) {
        cardDaoImpl.updateBalance(card);
    }
}
