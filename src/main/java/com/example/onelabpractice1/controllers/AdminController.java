package com.example.onelabpractice1.controllers;

import com.example.onelabpractice1.enums.Response;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.requests.UpdateRequest;
import com.example.onelabpractice1.service.CardService;
import com.example.onelabpractice1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController()
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final CardService cardService;

    @Autowired
    public AdminController(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }

    @GetMapping ("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getAllUsersSortByName")
    public List<User> getAllUsersSortByName() {
        return userService.getAllSortByName();
    }

    @GetMapping("/getAllUserWithName")
    public List<User> getAllUserWithName(@RequestParam String name) {
        return userService.getAllWithName(name);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Response> update(@Valid @RequestBody UpdateRequest updateRequest) {
        if (!userService.isPhoneNumberExist(updateRequest.getPhoneNumber())) {
            return ResponseEntity.ok(Response.PHONE_NUMBER_NOT_FOUND);
        }

        userService.updateUser(updateRequest);
        return ResponseEntity.ok(Response.USER_UPDATED);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Response> delete(@RequestParam String phoneNumber) {
        if (!userService.isPhoneNumberExist(phoneNumber)) {
            return ResponseEntity.ok(Response.PHONE_NUMBER_NOT_FOUND);
        }

        User user = userService.getByPhoneNumber(phoneNumber);
        userService.deleteUser(phoneNumber);
        cardService.deleteCard(user.getCard());
        return ResponseEntity.ok(Response.DELETED_SUCCESSFULLY);
    }
}
