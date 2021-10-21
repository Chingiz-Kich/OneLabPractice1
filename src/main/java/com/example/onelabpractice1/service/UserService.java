package com.example.onelabpractice1.service;

import com.example.onelabpractice1.dto.Card;
import com.example.onelabpractice1.dto.User;
import com.example.onelabpractice1.helper.CardHelper;
import com.example.onelabpractice1.repository.CardRepo;
import com.example.onelabpractice1.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepo userRepository;
    private CardRepo cardRepository;

    @Autowired
    public UserService(UserRepo userRepository, CardRepo cardRepository) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    public boolean createUser(User user) {
        if (userRepository.existByPhoneNumber(user.getPhoneNumber())) {
            return false;
        }

        if (cardRepository.isCardExist(user.getCard())) {
            String newCardNumber = CardHelper.getNumber();
            Card newCard = new Card(newCardNumber);
            User updatedUser = userRepository.getByPhoneNumber(user.getPhoneNumber());
            updatedUser.setCard(newCard);
            userRepository.saveUser(updatedUser);
            cardRepository.saveCard(newCard);
            return true;
        }

        userRepository.saveUser(user);
        cardRepository.saveCard(user.getCard());
        return true;
    }

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    public List<User> getAllSortByName() {
        List<User> users = userRepository.getAll();
        users.sort(User.COMPARE_BY_NAME);
        return users;
    }

    public List<User> getAllWithName(String name) {
        List<User> listUser = new ArrayList<>();
        for (User user : userRepository.getAll()) {
            if (user.getName().equals(name)) {
                listUser.add(user);
            }
        }
        return listUser;
    }

    public User getByPhoneNumber(String phoneNumber) {
        return userRepository.getByPhoneNumber(phoneNumber);
    }
}
