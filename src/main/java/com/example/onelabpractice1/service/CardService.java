package com.example.onelabpractice1.service;

import com.example.onelabpractice1.constants.Constant;
import com.example.onelabpractice1.helper.CardHelper;
import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.repository.CardRepository;
import com.example.onelabpractice1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    @Autowired
    public CardService(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
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
        return cardRepository.findAll()
                .stream()
                .sorted(Card.COMPARE_BY_BALANCE)
                .collect(toList());
    }

    public Card getCardByCardNumber(String cardNumber) {
        return Optional.of(cardRepository.findCardByNumber(cardNumber)).orElseThrow(() -> new NoSuchElementException(Constant.CARD_NOT_FOUND_EXCEPTION));
    }


    public void depositByPhoneNumber(String phoneNumber, double money) {
        Card card = Optional.of(userRepository.findByPhoneNumber(phoneNumber).getCard()).orElseThrow(() -> new NoSuchElementException(Constant.CARD_NOT_FOUND_EXCEPTION));
        card.setBalance(card.getBalance() + money);
        cardRepository.save(card);
    }

    public void withdrawByPhoneNumber(String phoneNumber, double money) {
        Card card = Optional.of(userRepository.findByPhoneNumber(phoneNumber).getCard()).orElseThrow(() -> new NoSuchElementException(Constant.CARD_NOT_FOUND_EXCEPTION));
        card.setBalance(card.getBalance() - money);
        cardRepository.save(card);
    }

    public boolean isEnoughBalance(String phoneNumber, double money) {
        return userRepository.findByPhoneNumber(phoneNumber).getCard().getBalance() >= money;
    }
}
