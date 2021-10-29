package com.example.onelabpractice1.service;

import com.example.onelabpractice1.models.Transfer;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.repository.CardRepository;
import com.example.onelabpractice1.repository.TransferRepository;
import com.example.onelabpractice1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransferService {
    private TransferRepository transferRepository;
    private UserRepository userRepository;
    private CardRepository cardRepository;

    @Autowired TransferService(TransferRepository transferRepository, UserRepository userRepository, CardRepository cardRepository) {
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

        cardRepository.updateBalance(sender.getCard().getNumber(), senderBalance);
        cardRepository.updateBalance(recipient.getCard().getNumber(), recipientBalance);

        Transfer transfer = new Transfer();
        transfer.setSenderPhoneNumber(sender.getPhoneNumber());
        transfer.setRecipientPhoneNumber(recipient.getPhoneNumber());
        transfer.setMoney(money);
        transfer.setTransferDate(Date.valueOf(LocalDate.now()));
        transferRepository.save(transfer);
    }

    public List<Transfer> getAllTransfersByDate() {
        List<Transfer> transfersList = new ArrayList<>();
        transfersList = transferRepository.findAll();
        transfersList.sort(Transfer.COMPARE_BY_DATE);
        return transfersList;
    }

    public List<Transfer> getTransferHistorySenderRecipient(User sender, User recipient) {
        List<Transfer> transfersList = new ArrayList<>();

        for (Transfer transfer : transferRepository.findAll()) {
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
        return !userRepository.existsUserByPhoneNumber(user.getPhoneNumber());
    }
}
