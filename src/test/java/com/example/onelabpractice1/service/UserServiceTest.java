package com.example.onelabpractice1.service;

import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.repository.CardRepository;
import com.example.onelabpractice1.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    CardRepository cardRepository;
    @InjectMocks
    UserService sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateUser() {
        when(userRepository.existsUserByPhoneNumber(anyString())).thenReturn(true);

        boolean result = sut.createUser(new User(null, null, "phoneNumber"));
        Assertions.assertEquals(true, result);
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.<User>asList(new User("name", "surname", "phoneNumber")));

        List<User> result = sut.getAllUsers();
        Assertions.assertEquals(Arrays.<User>asList(new User("name", "surname", "phoneNumber")), result);
    }

    @Test
    void testGetAllSortByName() {
        when(userRepository.findAll()).thenReturn(Arrays.<User>asList(new User("name", "surname", "phoneNumber")));

        List<User> result = sut.getAllSortByName();
        Assertions.assertEquals(Arrays.<User>asList(new User("name", "surname", "phoneNumber")), result);
    }

    @Test
    void testGetAllWithName() {
        when(userRepository.findAll()).thenReturn(Arrays.<User>asList(new User("name", null, null)));

        List<User> result = sut.getAllWithName("name");
        Assertions.assertEquals(Arrays.<User>asList(new User("name", null, null)), result);
    }

    @Test
    void testGetByPhoneNumber() {
        when(userRepository.getByPhoneNumber(anyString())).thenReturn(new User("name", "surname", "phoneNumber"));

        User result = sut.getByPhoneNumber("phoneNumber");
        Assertions.assertEquals(new User("name", "surname", "phoneNumber"), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme