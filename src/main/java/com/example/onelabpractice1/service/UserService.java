package com.example.onelabpractice1.service;

import com.example.onelabpractice1.aspects.ExceptionChecker;
import com.example.onelabpractice1.helper.CardHelper;
import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.repository.CardRepository;
import com.example.onelabpractice1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private CardRepository cardRepository;

    @Autowired
    public UserService(UserRepository userRepository, CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    @ExceptionChecker
    public boolean createUser(User user) {
        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            return false;
        }

        String number = CardHelper.getNumber();
        Card card = CardHelper.createCard(number);
        user.setCard(card);

        userRepository.save(user);
        cardRepository.save(card);
        return true;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllSortByName() {
        List<User> users = userRepository.findAll();
        users.sort(User.COMPARE_BY_NAME);
        return users;
    }

    public List<User> getAllWithName(String name) {
        List<User> listUser = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            if (user.getName().equals(name)) {
                listUser.add(user);
            }
        }
        return listUser;
    }

    public User getByPhoneNumber(String phoneNumber) {
        return userRepository.getByPhoneNumber(phoneNumber);
    }

    private boolean addCardToUser(String phoneNumber, Card card) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        user.setCard(card);
        userRepository.save(user);
        return true;
    }
}