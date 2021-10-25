package com.example.onelabpractice1.service;

import com.example.onelabpractice1.models.Transfer;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.repository.CardRepo;
import com.example.onelabpractice1.repository.TransferRepo;
import com.example.onelabpractice1.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransferService {
    private TransferRepo transferRepository;
    private UserRepo userRepository;
    private CardRepo cardRepository;

    @Autowired TransferService(TransferRepo transferRepository, UserRepo userRepository, CardRepo cardRepository) {
        this.transferRepository = transferRepository;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    public void makeTransfer(User sender, User recipient, double money) {
        if (!existUser(sender) || !existUser(recipient)) {
            System.out.println("User does not exist!");
            return;
        }

        if (!enoughBalance(sender, money)) {
            System.out.println("Balance is not enough");
            return;
        }

        double senderBalance = sender.getCard().getBalance();
        sender.getCard().setBalance(senderBalance - money);

        double recipientBalance = recipient.getCard().getBalance();
        recipient.getCard().setBalance(recipientBalance + money);

        cardRepository.updateBalance(sender.getCard());
        cardRepository.updateBalance(recipient.getCard());

        Transfer transfer = new Transfer();
        transfer.setSenderPhoneNumber(sender.getPhoneNumber());
        transfer.setRecipientPhoneNumber(recipient.getPhoneNumber());
        transfer.setMoney(money);
        transfer.setTransferDate(Date.valueOf(LocalDate.now()));
        transferRepository.saveTransfer(transfer);
    }

    public List<Transfer> getAllTransfersByDate(String date) {
        List<Transfer> transfersList = new ArrayList<>();
        LocalDate localDate = LocalDate.parse(date);

        for (Transfer transfer : transferRepository.getHistoryList()) {
            if (transfer.getTransferDate().equals(localDate)) {
                transfersList.add(transfer);
            }
        }

        return transfersList;
    }

    public List<Transfer> getTransferHistorySenderRecipient(User sender, User recipient) {
        List<Transfer> transfersList = new ArrayList<>();

        for (Transfer transfer : transferRepository.getHistoryList()) {
            if (transfer.getSenderPhoneNumber().equals(sender.getPhoneNumber()) && transfer.getRecipientPhoneNumber().equals(recipient.getPhoneNumber())) {
                transfersList.add(transfer);
            }
        }

        return transfersList;
    }

    private boolean enoughBalance(User user, double money) {
        return user.getCard().getBalance() >= money;
    }

    private boolean existUser(User user) {
        return userRepository.existByPhoneNumber(user.getPhoneNumber());
    }
}
