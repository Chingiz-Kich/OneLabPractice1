package com.example.onelabpractice1;

import com.example.onelabpractice1.dto.User;
import com.example.onelabpractice1.service.CardService;
import com.example.onelabpractice1.service.TransferService;
import com.example.onelabpractice1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("Application")
public class Application {
    private static UserService userService;
    private static CardService cardService;
    private static TransferService transferService;

    @Autowired
    public void setUserService(UserService userService) {
        Application.userService = userService;
    }

    @Autowired
    public void setCardService(CardService cardService) {
        Application.cardService = cardService;
    }

    @Autowired
    public void setTransferService(TransferService transferService) {
        Application.transferService = transferService;
    }

    public static void start() {
        User u1 = new User("Chingiz", "Kissikov", "87751994074");
        User u2 = new User("Conor", "MacGregor", "87023550102");
        User u3 = new User("Cristiano", "Ronaldo", "87771253321");
        User u4 = new User("Adam", "Adamovich", "87072150358");

        userService.createUser(u1);
        userService.createUser(u2);
        userService.createUser(u3);
        userService.createUser(u4);

        System.out.println(cardService.getCardByNumber(u1.getCard().getNumber()));

        transferService.makeTransfer(u1, u2, 200);
        transferService.makeTransfer(u3, u2, 300);

        System.out.println("\n" + "Sorted users: ");
        System.out.println(userService.getAllSortByName());


        System.out.println("\n" + "Sorted by balance: ");
        System.out.println(cardService.getAllByBalanceASC());

        System.out.println("\n" + "Transfer History: ");
        System.out.println(transferService.getTransferHistorySenderRecipient(u1, u2));
    }
}
