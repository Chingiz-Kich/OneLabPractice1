package com.example.onelabpractice1.controllers;


import com.example.onelabpractice1.constants.Constants;
import com.example.onelabpractice1.requests.DepositRequest;
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
    private UserService userService;
    private CardService cardService;

    @Autowired
    public UserController(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }


    @PostMapping("/deposit")
    // @PreAuthorize("hasAnyAuthority('developers:read')")
    public ResponseEntity<?> deposit(@RequestBody @Valid DepositRequest depositRequest) {
        if (!userService.isPhoneNumberExist(depositRequest.getPhoneNumber())) {
            return ResponseEntity.ok(Constants.PHONE_NUMBER_NOT_FOUND);
        }

        cardService.depositByPhoneNumber(depositRequest.getPhoneNumber(), depositRequest.getMoney());
        return ResponseEntity.ok(Constants.OK);
    }
}
