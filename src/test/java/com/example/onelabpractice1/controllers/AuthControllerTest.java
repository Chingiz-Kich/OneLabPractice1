package com.example.onelabpractice1.controllers;

import com.example.onelabpractice1.Prototype;
import com.example.onelabpractice1.constants.Constants;
import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.requests.UserRequest;
import com.example.onelabpractice1.service.CardService;
import com.example.onelabpractice1.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.mockito.Mockito.*;

class AuthControllerTest {
    @Mock
    AuthenticationManager authenticationManager;
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

    @Test
    void testRegisterUser() {
        UserRequest userRequest1 = Prototype.userRequestAaa();
        when(userService.registration(userRequest1, false)).thenReturn(true);

        Card card1 = cardService.createCard();

        when(userService.addCardToUserByPhoneNumber("phoneNumber1", card1)).thenReturn(true);

        // when(cardService.createCard()).thenReturn(new Card("number", 0d));

        ResponseEntity<?> result = authController.registerUser(userRequest1);
        Assertions.assertEquals(ResponseEntity.ok(Constants.REGISTERED_SUCCESSFULLY), result);
    }

    @Test
    void testRegisterUserIfFalse() {
        UserRequest userRequest2 = Prototype.userRequest();
        when(userService.registration(userRequest2, true)).thenReturn(true);

        ResponseEntity<?> result = authController.registerUser(userRequest2);
        Assertions.assertEquals(ResponseEntity.ok(Constants.FAILED), result);
    }

    @Test
    void testRegisterAdmin() {
        UserRequest userRequest1 = Prototype.userRequestAaa();
        when(userService.registration(userRequest1, true)).thenReturn(true);

        Card card1 = cardService.createCard();

        when(userService.addCardToUserByPhoneNumber("phoneNumber1", card1)).thenReturn(true);

        // when(cardService.createCard()).thenReturn(new Card("number", 0d));

        ResponseEntity<?> result = authController.registerAdmin(userRequest1);
        Assertions.assertEquals(ResponseEntity.ok(Constants.ADMIN_CREATED), result);
    }

    @Test
    void testRegisterAdminIfFalse() {
        UserRequest userRequest2 = Prototype.userRequest();
        when(userService.registration(userRequest2, false)).thenReturn(true);

        ResponseEntity<?> result = authController.registerAdmin(userRequest2);
        Assertions.assertEquals(ResponseEntity.ok(Constants.FAILED), result);
    }

/*    @Test
    void testUserLogin() {
        LoginRequest loginRequest1 = Prototype.loginRequestsAaa();
        User user1 = Prototype.userAaa();

        // when(jwtTokenProvider.createToken(loginRequest1.getPhoneNumber())).thenReturn(jwtTokenProvider.createToken(loginRequest1.getPhoneNumber()));
        when(userService.getByPhoneNumber("phoneNumber1")).thenReturn(user1);

        ResponseEntity<?> result = authController.userLogin(loginRequest1);
        Assertions.assertEquals(ResponseEntity.ok(authentication(loginRequest1, false)), result);
    }*/

/*    @Test
    void testAdminLogin() {
        LoginRequest loginRequest1 = Prototype.loginRequestsAaa();
        User user1 = Prototype.userAaa();

        // when(jwtTokenProvider.createToken(anyString())).thenReturn("createTokenResponse");
        when(userService.getByPhoneNumber("phoneNumber1")).thenReturn(user1);

        ResponseEntity<?> result = authController.adminLogin(loginRequest1);
        Assertions.assertEquals(ResponseEntity.ok(authentication(loginRequest1, true)), result);
    }

    private Map<Object, Object> authentication(LoginRequest loginRequest, boolean isAdmin) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getPhoneNumber(), loginRequest.getPassword()));
        User user = userService.getByPhoneNumber(loginRequest.getPhoneNumber());
        String token = jwtTokenProvider.createToken(loginRequest.getPhoneNumber());
        Map<Object, Object> response = new HashMap<>();
        if (isAdmin) {
            response.put("Admin phone number: ", loginRequest.getPhoneNumber());
            response.put("token: ", token);
            return response;
        }
        response.put("User phone number: ", loginRequest.getPhoneNumber());
        response.put("token: ", token);
        return response;
    }*/
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme