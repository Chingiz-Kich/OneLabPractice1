package com.example.onelabpractice1.transactionalController;

import com.example.onelabpractice1.constants.Constants;
import com.example.onelabpractice1.requests.DepositRequest;
import com.example.onelabpractice1.service.CardService;
import com.example.onelabpractice1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactional")
public class TransactionalTest {
    private final UserService userService;
    private final CardService cardService;

    @Autowired
    public TransactionalTest(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }

    @PostMapping("/deposit")
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED)
    public ResponseEntity<?> deposit(@RequestBody @Valid DepositRequest depositRequest) {
        if (!userService.isPhoneNumberExist(depositRequest.getPhoneNumber())) {
            return ResponseEntity.ok(Constants.PHONE_NUMBER_NOT_FOUND);
        }

        cardService.depositByPhoneNumber(depositRequest.getPhoneNumber(), depositRequest.getMoney());

        if (userService.isPhoneNumberExist(depositRequest.getPhoneNumber())) {
            throw new RuntimeException("Exception to check");
        }

        return ResponseEntity.ok(Constants.OK);
    }


}
