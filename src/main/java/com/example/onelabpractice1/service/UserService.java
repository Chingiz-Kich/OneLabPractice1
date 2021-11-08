package com.example.onelabpractice1.service;

import com.example.onelabpractice1.aspects.ExceptionChecker;
import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.models.Role;
import com.example.onelabpractice1.models.Status;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.repository.UserRepository;
import com.example.onelabpractice1.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ExceptionChecker
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registration(UserRequest userRequest, boolean isAdmin) {
        if (userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())) {
            return false;
        }

        User user = new User(userRequest.getName(), userRequest.getSurname(), userRequest.getEmail(), userRequest.getPhoneNumber(), userRequest.getPassword());

        if (isAdmin) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }

        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return true;
    }

    public User getUserByPhoneNumberAndPassword(String phoneNumber, String password) {
        User user = userRepository.findUserByPhoneNumberAndPassword(phoneNumber, password);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("User not found");
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

    public boolean isPhoneNumberExist(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean addCardToUser(String phoneNumber, Card card) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        user.setCard(card);
        userRepository.save(user);
        return true;
    }
}