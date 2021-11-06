package com.example.onelabpractice1.service;

import com.example.onelabpractice1.helper.CardHelper;
import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.repository.CardRepository;
import com.example.onelabpractice1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    private CardRepository cardRepository;
    private UserRepository userRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card createCard() {
        String cardNumber = CardHelper.getNumber();
        Card card = new Card(cardNumber, 500);
        cardRepository.save(card);
        return card;
    }

    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    public List<Card> getAllByBalanceASC() {
        List<Card> cards = getAll();
        cards.sort(Card.COMPARE_BY_BALANCE);
        return cards;
    }

    public Card getCardByCardNumber(String cardNumber) {
        return cardRepository.getCardByNumber(cardNumber);
    }


    public void deposit(String phoneNumber, double money) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        Card card = user.getCard();
        card.setBalance(card.getBalance() + money);
        cardRepository.save(card);
    }

    public void withdraw(String cardNumber, double money) {
        Card card = cardRepository.getCardByNumber(cardNumber);
        card.setBalance(card.getBalance() - money);
        cardRepository.save(card);
    }

    public boolean isCardNumberExist(String cardNumber) {
        return cardRepository.existsCardByNumber(cardNumber);
    }
}
