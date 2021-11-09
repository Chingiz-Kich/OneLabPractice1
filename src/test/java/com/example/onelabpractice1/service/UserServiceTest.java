package com.example.onelabpractice1.service;

import com.example.onelabpractice1.Prototype;
import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.models.Role;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.repository.RoleRepository;
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

import static com.example.onelabpractice1.Prototype.userB;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;
    @InjectMocks
    UserService sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAdminRegistration1() {
        UserRequest userRequest = Prototype.userRequest();

        when(userRepository.existsByPhoneNumber("87751994074")).thenReturn(true);
        when(roleRepository.existsByName("ROLE_ADMIN")).thenReturn(true);

        boolean result = sut.registration(userRequest, true);
        Assertions.assertTrue(result);
    }

    @Test
    void testAdminRegistration2() {
        UserRequest userRequest = Prototype.userRequest();

        when(userRepository.existsByPhoneNumber("87751994074")).thenReturn(true);
        when(roleRepository.existsByName("ROLE_ADMIN")).thenReturn(false);

        boolean result = sut.registration(userRequest, true);
        Assertions.assertTrue(result);
    }

    @Test
    void testUserRegistration1() {
        UserRequest userRequest = Prototype.userRequest();

        when(userRepository.existsByPhoneNumber("87751994074")).thenReturn(true);
        when(roleRepository.existsByName("ROLE_USER")).thenReturn(true);

        boolean result = sut.registration(userRequest, false);
        Assertions.assertTrue(result);
    }

    @Test
    void testUserRegistration2() {
        UserRequest userRequest = Prototype.userRequest();

        when(userRepository.existsByPhoneNumber("87751994074")).thenReturn(true);
        when(roleRepository.existsByName("ROLE_USER")).thenReturn(false);

        boolean result = sut.registration(userRequest, false);
        Assertions.assertTrue(result);
    }

    @Test
    void testGetUserByLoginPassword() {
        User testUser = userB();

        userRepository.save(testUser);

        when(userRepository.findByPhoneNumber("87751994074")).thenReturn(testUser);
        when(userRepository.existsByPhoneNumberAndPassword("phoneNumber", "password")).thenReturn(true);
        when(userRepository.findUserByPhoneNumberAndPassword("phoneNumber", "password")).thenReturn(testUser);

        User result = sut.getUserByPhoneNumberAndPassword("phoneNumber", "password");
        Assertions.assertEquals(testUser, result);
    }

    @Test
    void testGetAllUsers() {
        User userTest = userB();

        List<User> userListTest = new ArrayList<>();
        userListTest.add(userTest);

        when(userRepository.findAll()).thenReturn(userListTest);
        List<User> result = sut.getAllUsers();
        Assertions.assertEquals(userListTest, result);
    }

    @Test
    void testGetAllSortByName() {
        User userTest1 = Prototype.userA();
        User userTest2 = userB();

        List<User> userListTest = new ArrayList<>();
        userListTest.add(userTest1);
        userListTest.add(userTest2);

        when(userRepository.findAll()).thenReturn(userListTest);

        List<User> result = sut.getAllSortByName();
        Assertions.assertEquals(userListTest, result);
    }

    @Test
    void testGetAllWithName() {
        User userTest1 = Prototype.userA();
        User userTest2 = Prototype.userB();

        List<User> userListTest = new ArrayList<>();
        userListTest.add(userTest1);
        userListTest.add(userTest2);

        when(userRepository.findAll()).thenReturn(userListTest);

        List<User> result = sut.getAllWithName("Aaa");

        userListTest.remove(userTest2);

        Assertions.assertEquals(userListTest, result);
    }

    @Test
    void testGetByPhoneNumber() {
        User user1 = Prototype.userA();

        when(userRepository.findByPhoneNumber("phoneNumber1")).thenReturn(user1);

        User result = sut.getByPhoneNumber("phoneNumber1");

        Assertions.assertEquals(user1, result);
    }

    @Test
    void testIsPhoneNumberExist() {
        when(userRepository.existsByPhoneNumber("phoneNumber1")).thenReturn(true);

        boolean result = sut.isPhoneNumberExist("phoneNumber1");
        Assertions.assertTrue(result);
    }

    @Test
    void testAddCardToUser() {
        User user1 = Prototype.userA();

        when(userRepository.findByPhoneNumber("phoneNumber1")).thenReturn(user1);

        boolean result = sut.addCardToUserByPhoneNumber("phoneNumber1", new Card("newNumber", 0d));
        Assertions.assertTrue(result);
    }

    @Test
    void testGetUserByPhoneNumberAndRole() {
        User user1 = Prototype.userA();
        Role role1 = Prototype.roleUser();
        user1.setRole(role1);
        List<User> users = new ArrayList<>();
        users.add(user1);
        role1.setUsers(users);

        when(roleRepository.findByName(role1.getName())).thenReturn(role1);
        when(userRepository.findByPhoneNumberAndRole(user1.getPhoneNumber(), role1)).thenReturn(user1);

        User result = sut.getByPhoneNumberAndRole(user1.getPhoneNumber(), role1.getName());
        Assertions.assertEquals(user1, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme