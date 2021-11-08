package com.example.onelabpractice1;

import com.example.onelabpractice1.helper.CardHelper;
import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.models.Transfer;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.requests.DepositRequest;
import com.example.onelabpractice1.requests.LoginRequest;
import com.example.onelabpractice1.requests.TransferByPhoneRequest;
import com.example.onelabpractice1.requests.UserRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Prototype {
    public static User userAaa() {
        User userTest1 = new User("Aaa", "Bbb", "email", "phoneNumber1", "password");
        userTest1.setCard(new Card("cardA", 500));
        return userTest1;
    }

    public static User userName() {
        User userTest2 = new User("name",  "surname", "email", "phoneNumber2", "password");
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

    public static UserRequest userRequestAaa() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("Aaa");
        userRequest.setSurname("Bbb");
        userRequest.setEmail("email");
        userRequest.setPhoneNumber("phoneNumber1");
        userRequest.setPassword("password");
        return userRequest;
    }

    public static UserRequest userRequestName() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("name");
        userRequest.setSurname("surname");
        userRequest.setEmail("email");
        userRequest.setPhoneNumber("phoneNumber2");
        userRequest.setPassword("password");
        return userRequest;
    }

    public static DepositRequest depositRequestAaa() {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setPhoneNumber("phoneNumber1");
        depositRequest.setMoney(500);
        return depositRequest;
    }

    public static LoginRequest loginRequestsAaa() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPhoneNumber("phoneNumber1");
        loginRequest.setPassword("password");
        return loginRequest;
    }

    public static TransferByPhoneRequest transferByPhoneRequest() {
        TransferByPhoneRequest transferByPhoneRequest = new TransferByPhoneRequest();
        transferByPhoneRequest.setSenderPhoneNumber("phoneNumber1");
        transferByPhoneRequest.setRecipientPhoneNumber("phoneNumber2");
        transferByPhoneRequest.setMoney(500);
        return transferByPhoneRequest;
    }
}
