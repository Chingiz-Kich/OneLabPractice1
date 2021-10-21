package com.example.onelabpractice1.repository;

import com.example.onelabpractice1.dto.Transfer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransferRepo implements ITransferRepo{
    List<Transfer> historyList = new ArrayList<>();

    @Override
    public List<Transfer> getHistoryList() {
        return historyList;
    }

    @Override
    public void saveTransfer(Transfer transfer) {
        historyList.add(transfer);
    }
}
