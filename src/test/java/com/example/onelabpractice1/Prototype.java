package com.example.onelabpractice1;

import com.example.onelabpractice1.helper.CardHelper;
import com.example.onelabpractice1.models.*;
import com.example.onelabpractice1.requests.*;

import java.util.*;

public class Prototype {
    public static User userA() {
        User userTest1 = new User("Aaa", "ASurname", "email", "phoneNumber1", "password");
        userTest1.setCard(new Card("cardA", 500));
        return userTest1;
    }

    public static User userB() {
        User userTest2 = new User("Bbb",  "Bsurname", "email", "phoneNumber2", "password");
        userTest2.setCard(new Card("cardNumber", 500));
        return userTest2;
    }

    public static UserRequest userRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Chingiz");
        userRequest.setSurname("Kissikov");
        userRequest.setEmail("www@mail.ru");
        userRequest.setPhoneNumber("877519940874");
        userRequest.setPassword("qwerty");
        return userRequest;
    }

    public static Card createCard1() {
        String cardNumber = CardHelper.getNumber();
        Card card = new Card(cardNumber, 500);
        return card;
    }

    public static Card createCard2() {
        String cardNumber = CardHelper.getNumber();
        return new Card(cardNumber, 300);
    }

    public static Transfer transfer1() {
        String senderPhoneNumber = "phoneNumber1";
        String recipientPhoneNumber = "phoneNumber2";
        double money = 500;
        Date transferDate = new GregorianCalendar(2021, Calendar.NOVEMBER, 8, 11, 18).getTime();
        return new Transfer(senderPhoneNumber, recipientPhoneNumber, money, transferDate);
    }

    public static Transfer transfer2() {
        String senderPhoneNumber = "phoneNumber2";
        String recipientPhoneNumber = "phoneNumber1";
        double money = 500;
        Date transferDate = new GregorianCalendar(2021, Calendar.NOVEMBER, 8, 12, 18).getTime();
        return new Transfer(senderPhoneNumber, recipientPhoneNumber, money, transferDate);
    }

    public static UserRequest userRequestA() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Aaa");
        userRequest.setSurname("ASurname");
        userRequest.setEmail("email");
        userRequest.setPhoneNumber("phoneNumber1");
        userRequest.setPassword("password");
        return userRequest;
    }

    public static UserRequest userRequestB() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Bbb");
        userRequest.setSurname("BSurname");
        userRequest.setEmail("email");
        userRequest.setPhoneNumber("phoneNumber2");
        userRequest.setPassword("password");
        return userRequest;
    }

    public static DepositRequest depositRequestA() {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setPhoneNumber("phoneNumber1");
        depositRequest.setMoney(500);
        return depositRequest;
    }

    public static LoginRequest loginRequestsA() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPhoneNumber("phoneNumber1");
        loginRequest.setPassword("password");
        return loginRequest;
    }

    public static TransferByPhoneRequest transferByPhoneRequest() {
        TransferByPhoneRequest transferByPhoneRequest = new TransferByPhoneRequest();
        transferByPhoneRequest.setSenderPhoneNumber("phoneNumber1");
        transferByPhoneRequest.setRecipientPhoneNumber("phoneNumber2");
        transferByPhoneRequest.setMoney(100);
        return transferByPhoneRequest;
    }

    public static Role roleUser() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        role.setStatus(Status.ACTIVE);
        return role;
    }

    public static Role roleAdmin() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_ADMIN");
        role.setStatus(Status.ACTIVE);
        return role;
    }

    public static WithdrawRequest withdrawRequestA() {
        WithdrawRequest withdrawRequest = new WithdrawRequest();
        withdrawRequest.setPhoneNumber("phoneNumber1");
        withdrawRequest.setMoney(100);
        return withdrawRequest;
    }
}
