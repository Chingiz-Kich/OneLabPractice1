package com.example.onelabpractice1.service;

import com.example.onelabpractice1.aspects.ExceptionChecker;
import com.example.onelabpractice1.constants.Constant;
import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.models.Role;
import com.example.onelabpractice1.enums.Status;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.repository.RoleRepository;
import com.example.onelabpractice1.repository.UserRepository;
import com.example.onelabpractice1.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@ExceptionChecker
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public boolean registration(UserRequest userRequest, boolean isAdmin) {
        if (userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())) {
            return false;
        }

        User user = new User(userRequest.getName(), userRequest.getSurname(), userRequest.getEmail(), userRequest.getPhoneNumber(), userRequest.getPassword());

        if (isAdmin) {
            if (!roleRepository.existsByName(Constant.ROLE_ADMIN)) {
                Role adminRole = new Role();
                adminRole.setName(Constant.ROLE_ADMIN);
                adminRole.setStatus(Status.ACTIVE);
                roleRepository.save(adminRole);
            }
            Role roleAdmin = roleRepository.findByName(Constant.ROLE_ADMIN);
            user.setRole(roleAdmin);

        } else {
            if (!roleRepository.existsByName(Constant.ROLE_USER)) {
                Role userRole = new Role();
                userRole.setName(Constant.ROLE_USER);
                userRole.setStatus(Status.ACTIVE);
                roleRepository.save(userRole);
            }
            Role roleUser = roleRepository.findByName(Constant.ROLE_USER);
            user.setRole(roleUser);
        }

        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return true;
    }

    public User getUserByPhoneNumberAndPassword(String phoneNumber, String password) {
        return Optional.of(userRepository.findUserByPhoneNumberAndPassword(phoneNumber, password)).orElseThrow(() -> new NoSuchElementException(Constant.USER_NOT_FOUND_EXCEPTION));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllSortByName() {
        return userRepository.findAll()
                .stream()
                .sorted(User.COMPARE_BY_NAME)
                .collect(toList());
    }

    public List<User> getAllWithName(String name) {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getName().equals(name))
                .collect(toList());
    }

    public User getByPhoneNumber(String phoneNumber) {
        return Optional.of(userRepository.findByPhoneNumber(phoneNumber)).orElseThrow(() -> new NoSuchElementException(Constant.USER_NOT_FOUND_EXCEPTION));
    }

    public boolean isPhoneNumberExist(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean addCardToUserByPhoneNumber(String phoneNumber, Card card) {
        User user = Optional.of(userRepository.findByPhoneNumber(phoneNumber)).orElseThrow(() -> new NoSuchElementException(Constant.USER_NOT_FOUND_EXCEPTION));
        user.setCard(card);
        userRepository.save(user);
        return true;
    }

    public User getByPhoneNumberAndRole(String phoneNumber, String roleName) {
        Role role = Optional.of(roleRepository.findByName(roleName)).orElseThrow(() -> new NoSuchElementException(Constant.USER_NOT_FOUND_EXCEPTION));
        return Optional.of(userRepository.findByPhoneNumberAndRole(phoneNumber, role)).orElseThrow(() -> new NoSuchElementException(Constant.USER_NOT_FOUND_EXCEPTION));

    }
}