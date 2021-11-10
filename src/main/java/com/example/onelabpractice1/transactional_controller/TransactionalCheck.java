package com.example.onelabpractice1.transactional_controller;

import com.example.onelabpractice1.enums.Response;
import com.example.onelabpractice1.requests.DepositRequest;
import com.example.onelabpractice1.service.CardService;
import com.example.onelabpractice1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/transactional")
public class TransactionalCheck {
    private final UserService userService;
    private final CardService cardService;

    @Autowired
    public TransactionalCheck(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }

    @PostMapping("/deposit")
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED)
    public ResponseEntity<Response> deposit(@RequestBody @Valid DepositRequest depositRequest) {
        if (!userService.isPhoneNumberExist(depositRequest.getPhoneNumber())) {
            return ResponseEntity.ok(Response.PHONE_NUMBER_NOT_FOUND);
        }

        cardService.depositByPhoneNumber(depositRequest.getPhoneNumber(), depositRequest.getMoney());

        if (userService.isPhoneNumberExist(depositRequest.getPhoneNumber())) {
            throw new NoSuchElementException("Exception to check");
        }

        return ResponseEntity.ok(Response.OK);
    }
}
