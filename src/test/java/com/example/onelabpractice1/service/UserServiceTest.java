package com.example.onelabpractice1.service;

import com.example.onelabpractice1.Prototype;
import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.repository.UserRepository;
import com.example.onelabpractice1.requests.UserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.example.onelabpractice1.Prototype.userName;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegistration() {
        UserRequest userRequest = Prototype.userRequest();

        when(userRepository.existsByPhoneNumber("87751994074")).thenReturn(true);

        boolean result = userService.registration(userRequest, true);
        Assertions.assertTrue(result);
    }

    @Test
    void testGetUserByLoginPassword() {
        User testUser = userName();

        userRepository.save(testUser);

        when(userRepository.getByPhoneNumber("87751994074")).thenReturn(testUser);
        when(userRepository.existsByPhoneNumberAndPassword("phoneNumber", "password")).thenReturn(true);
        when(userRepository.findUserByPhoneNumberAndPassword("phoneNumber", "password")).thenReturn(testUser);

        User result = userService.getUserByPhoneNumberAndPassword("phoneNumber", "password");
        Assertions.assertEquals(testUser, result);
    }

    @Test
    void testGetAllUsers() {
        User userTest = userName();

        List<User> userListTest = new ArrayList<>();
        userListTest.add(userTest);

        when(userRepository.findAll()).thenReturn(userListTest);
        List<User> result = userService.getAllUsers();
        Assertions.assertEquals(userListTest, result);
    }

    @Test
    void testGetAllSortByName() {
        User userTest1 = Prototype.userAaa();
        User userTest2 = userName();

        List<User> userListTest = new ArrayList<>();
        userListTest.add(userTest1);
        userListTest.add(userTest2);

        when(userRepository.findAll()).thenReturn(userListTest);

        List<User> result = userService.getAllSortByName();
        Assertions.assertEquals(userListTest, result);
    }

    @Test
    void testGetAllWithName() {
        User userTest1 = Prototype.userAaa();
        User userTest2 = userName();

        List<User> userListTest = new ArrayList<>();
        userListTest.add(userTest1);
        userListTest.add(userTest2);

        when(userRepository.findAll()).thenReturn(userListTest);

        List<User> result = userService.getAllWithName("name");

        userListTest.remove(userTest1);

        Assertions.assertEquals(userListTest, result);
    }

    @Test
    void testGetByPhoneNumber() {
        User user1 = Prototype.userAaa();

        when(userRepository.getByPhoneNumber("phoneNumber1")).thenReturn(user1);

        User result = userService.getByPhoneNumber("phoneNumber1");

        Assertions.assertEquals(user1, result);
    }

    @Test
    void testIsPhoneNumberExist() {
        when(userRepository.existsByPhoneNumber("phoneNumber1")).thenReturn(true);

        boolean result = userService.isPhoneNumberExist("phoneNumber1");
        Assertions.assertTrue(result);
    }

    @Test
    void testAddCardToUser() {
        User user1 = Prototype.userAaa();

        when(userRepository.findByPhoneNumber("phoneNumber1")).thenReturn(user1);

        boolean result = userService.addCardToUserByPhoneNumber("phoneNumber1", new Card("newNumber", 0d));
        Assertions.assertTrue(result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme