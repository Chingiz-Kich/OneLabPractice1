package com.example.onelabpractice1.controllers;


import com.example.onelabpractice1.constants.Constants;
import com.example.onelabpractice1.requests.DepositRequest;
import com.example.onelabpractice1.requests.WithdrawRequest;
import com.example.onelabpractice1.service.CardService;
import com.example.onelabpractice1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Validated
@RestController()
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final CardService cardService;

    @Autowired
    public UserController(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }


    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody @Valid DepositRequest depositRequest) {
        if (!userService.isPhoneNumberExist(depositRequest.getPhoneNumber())) {
            return ResponseEntity.ok(Constants.PHONE_NUMBER_NOT_FOUND);
        }

        cardService.depositByPhoneNumber(depositRequest.getPhoneNumber(), depositRequest.getMoney());
        return ResponseEntity.ok(Constants.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody @Valid WithdrawRequest withdrawRequest) {
        if (!userService.isPhoneNumberExist(withdrawRequest.getPhoneNumber())) {
            return ResponseEntity.ok(Constants.PHONE_NUMBER_NOT_FOUND);
        }

        if (!cardService.isEnoughBalance(withdrawRequest.getPhoneNumber(), withdrawRequest.getMoney())) {
            return ResponseEntity.ok(Constants.BALANCE_IS_NOT_ENOUGH);
        }

        cardService.withdrawByPhoneNumber(withdrawRequest.getPhoneNumber(), withdrawRequest.getMoney());
        return ResponseEntity.ok(Constants.OK);
    }
}
