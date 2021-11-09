package com.example.onelabpractice1.controllers;

import com.example.onelabpractice1.constants.Constants;
import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.requests.LoginRequest;
import com.example.onelabpractice1.requests.UserRequest;
import com.example.onelabpractice1.security.jwt.JwtTokenProvider;
import com.example.onelabpractice1.service.CardService;
import com.example.onelabpractice1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final CardService cardService;
    private final PasswordEncoder passwordEncoder;

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
        return ResponseEntity.ok(Constants.USER_REGISTERED_SUCCESSFULLY);
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
        return ResponseEntity.ok(Constants.ADMIN_REGISTERED_SUCCESSFULLY);
    }

    @PostMapping("/userLogin")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest) {
        try {
            String phoneNumber = loginRequest.getPhoneNumber();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phoneNumber, loginRequest.getPassword()));
            User user = userService.getByPhoneNumberAndRole(phoneNumber, "ROLE_USER");

            if (user == null) {
                throw new UsernameNotFoundException("User with phone number: " + phoneNumber + " not found");
            }

            String token = jwtTokenProvider.createToken(phoneNumber, user.getRole());

            Map<Object, Object> response = new HashMap<>();
            response.put("phone number ", phoneNumber);
            response.put("token ", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid phone number or password for User account");
        }
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<?> adminLogin (@RequestBody @Valid LoginRequest loginRequest) {
        try {
            String phoneNumber = loginRequest.getPhoneNumber();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phoneNumber, loginRequest.getPassword()));
            User user = userService.getByPhoneNumberAndRole(phoneNumber, "ROLE_ADMIN");

            if (user == null) {
                throw new UsernameNotFoundException("Admin with phone number: " + phoneNumber + " not found");
            }

            String token = jwtTokenProvider.createToken(phoneNumber, user.getRole());

            Map<Object, Object> response = new HashMap<>();
            response.put("phone number ", phoneNumber);
            response.put("token ", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid phone number or password for Admin account");
        }
    }
}
