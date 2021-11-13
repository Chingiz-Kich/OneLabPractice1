package com.example.onelabpractice1.controllers;

import com.example.onelabpractice1.Prototype;
import com.example.onelabpractice1.enums.Response;
import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.models.Role;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.requests.LoginRequest;
import com.example.onelabpractice1.requests.UserRequest;
import com.example.onelabpractice1.security.jwt.JwtTokenProvider;
import com.example.onelabpractice1.service.CardService;
import com.example.onelabpractice1.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class AuthControllerTest {
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    JwtTokenProvider jwtTokenProvider;
    @Mock
    UserService userService;
    @Mock
    CardService cardService;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testRegisterUser(boolean registerSuccess) {
        UserRequest userRequest1 = Prototype.userRequestA();
        Card card1 = Prototype.createCard1();

        when(userService.registration(userRequest1, false)).thenReturn(registerSuccess);

        when(cardService.createCard()).thenReturn(card1);
        when(userService.addCardToUserByPhoneNumber(userRequest1.getPhoneNumber(), card1)).thenReturn(true);

        ResponseEntity<?> result = authController.registerUser(userRequest1);

        if (registerSuccess) {
            Assertions.assertEquals(ResponseEntity.ok(Response.USER_REGISTERED_SUCCESSFULLY), result);
        } else {
            Assertions.assertEquals(ResponseEntity.ok(Response.FAILED), result);
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testRegisterAdmin(boolean registerSuccess) {
        UserRequest userRequest1 = Prototype.userRequestA();
        Card card1 = Prototype.createCard1();

        when(userService.registration(userRequest1, true)).thenReturn(registerSuccess);
        when(cardService.createCard()).thenReturn(card1);
        when(userService.addCardToUserByPhoneNumber(userRequest1.getPhoneNumber(), card1)).thenReturn(true);

        ResponseEntity<?> result = authController.registerAdmin(userRequest1);

        if (registerSuccess) {
            Assertions.assertEquals(ResponseEntity.ok(Response.ADMIN_REGISTERED_SUCCESSFULLY), result);
        } else {
            Assertions.assertEquals(ResponseEntity.ok(Response.FAILED), result);
        }
    }

    @Test
    void testUserLogin() {
        LoginRequest loginRequest1 = Prototype.loginRequestsA();
        User user1 = Prototype.userA();

        when(userService.getByPhoneNumberAndRole(loginRequest1.getPhoneNumber(), "ROLE_USER")).thenReturn(user1);
        when(jwtTokenProvider.createToken(loginRequest1.getPhoneNumber(), new Role())).thenReturn("token");

        Map<Object, Object> response = new HashMap<>();
        response.put("phone number ", loginRequest1.getPhoneNumber());
        response.put("token ", null);

        ResponseEntity<?> result = authController.userLogin(loginRequest1);
        Assertions.assertEquals(ResponseEntity.ok(response), result);
    }

    @Test
    void testAdminLogin() {
        LoginRequest loginRequest1 = Prototype.loginRequestsA();
        User user1 = Prototype.userA();

        when(userService.getByPhoneNumberAndRole(loginRequest1.getPhoneNumber(), "ROLE_ADMIN")).thenReturn(user1);
        when(jwtTokenProvider.createToken(loginRequest1.getPhoneNumber(), new Role())).thenReturn(null);

        Map<Object, Object> response = new HashMap<>();
        response.put("phone number ", loginRequest1.getPhoneNumber());
        response.put("token ", null);

        ResponseEntity<?> result = authController.adminLogin(loginRequest1);
        Assertions.assertEquals(ResponseEntity.ok(response), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme