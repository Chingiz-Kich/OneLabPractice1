package com.example.onelabpractice1.controllers;

import com.example.onelabpractice1.constants.Constants;
import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.requests.LoginRequests;
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

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        boolean result = userService.registration(userRequest);
        if (!result) {
            return ResponseEntity.ok(Constants.USER_ALREADY_EXIST);
        }
        Card card = cardService.createCard();
        userService.addCardToUser(userRequest.getPhoneNumber(), card);
        return ResponseEntity.ok(Constants.REGISTERED_SUCCESSFULLY);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginRequests loginRequests) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequests.getPhoneNumber(), loginRequests.getPassword()));
            User user = userService.getByPhoneNumber(loginRequests.getPhoneNumber());
            System.out.println("USER IS EXIST");
            String token = jwtTokenProvider.createToken(loginRequests.getPhoneNumber(), user.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("phone number: ", loginRequests.getPhoneNumber());
            response.put("token: ", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException ex) {
            return new ResponseEntity<>("Invalid phone number/password combination", HttpStatus.FORBIDDEN);
        }
    }
}
