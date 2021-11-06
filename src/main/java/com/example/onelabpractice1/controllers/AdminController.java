package com.example.onelabpractice1.controllers;


import com.example.onelabpractice1.constants.Constants;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.requests.TransferByPhoneRequest;
import com.example.onelabpractice1.service.CardService;
import com.example.onelabpractice1.service.TransferService;
import com.example.onelabpractice1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController()
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final CardService cardService;
    private final TransferService transferService;

    @Autowired
    public AdminController(UserService userService, CardService cardService, TransferService transferService) {
        this.userService = userService;
        this.cardService = cardService;
        this.transferService = transferService;
    }

    @GetMapping ("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/transferByPhoneNumber")
    public ResponseEntity<?> transferByPhone(@RequestBody TransferByPhoneRequest transfer) {
        if (!userService.isPhoneNumberExist(transfer.getSenderPhoneNumber()) || !userService.isPhoneNumberExist(transfer.getRecipientPhoneNumber())) {
            return ResponseEntity.ok(Constants.PHONE_NUMBER_NOT_FOUND);
        }

        if (!cardService.isEnoughBalance(transfer.getSenderPhoneNumber(), transfer.getMoney())) {
            return ResponseEntity.ok(Constants.BALANCE_IS_NOT_ENOUGH);
        }

        User u1 = userService.getByPhoneNumber(transfer.getSenderPhoneNumber());
        User u2 = userService.getByPhoneNumber(transfer.getRecipientPhoneNumber());
        transferService.makeTransfer(u1, u2, transfer.getMoney());
        return ResponseEntity.ok(Constants.OK);
    }
}