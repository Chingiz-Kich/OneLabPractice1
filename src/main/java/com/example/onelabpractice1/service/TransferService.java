package com.example.onelabpractice1.service;

import com.example.onelabpractice1.models.Transfer;
import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.repository.CardRepository;
import com.example.onelabpractice1.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransferService {
    private TransferRepository transferRepository;
    private CardRepository cardRepository;

    @Autowired TransferService(TransferRepository transferRepository, CardRepository cardRepository) {
        this.transferRepository = transferRepository;
        this.cardRepository = cardRepository;
    }

    public void makeTransfer(User sender, User recipient, double money) {
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
}
