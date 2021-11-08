package com.example.onelabpractice1.controllers;

import com.example.onelabpractice1.constants.Constants;
import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.requests.LoginRequest;
import com.example.onelabpractice1.requests.UserRequest;
import com.example.onelabpractice1.security.JwtTokenProvider;
import com.example.onelabpractice1.service.CardService;
import com.example.onelabpractice1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;
    private CardService cardService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, CardService cardService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.cardService = cardService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/userRegister")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        boolean result = userService.registration(userRequest, false);
        if (!result) {
            return ResponseEntity.ok(Constants.FAILED);
        }
        Card card = cardService.createCard();
        userService.addCardToUserByPhoneNumber(userRequest.getPhoneNumber(), card);
        return ResponseEntity.ok(Constants.REGISTERED_SUCCESSFULLY);
    }

    @PostMapping("/adminRegister")
    public ResponseEntity<?> registerAdmin(@RequestBody @Valid UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        boolean result = userService.registration(userRequest, true);
        if (!result) {
            return ResponseEntity.ok(Constants.FAILED);
        }

        Card card = cardService.createCard();
        userService.addCardToUserByPhoneNumber(userRequest.getPhoneNumber(), card);
        return ResponseEntity.ok(Constants.ADMIN_CREATED);
    }

    @PostMapping("/userLogin")
    public ResponseEntity<?> userLogin(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            return ResponseEntity.ok(authentication(loginRequest, false));
        } catch (AuthenticationException ex) {
            return new ResponseEntity<>("Invalid phone number/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<?> adminLogin (@RequestBody @Valid LoginRequest loginRequest) {
        try {
            return ResponseEntity.ok(authentication(loginRequest, true));
        } catch (AuthenticationException ex) {
            return new ResponseEntity<>("Invalid phone number/password combination", HttpStatus.FORBIDDEN);
        }
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
    }

}
