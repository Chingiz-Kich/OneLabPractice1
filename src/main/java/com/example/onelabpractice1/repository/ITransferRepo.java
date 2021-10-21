package com.example.onelabpractice1.repository;

import com.example.onelabpractice1.dto.Transfer;

import java.util.List;

public interface ITransferRepo {
    List<Transfer> getHistoryList();

    void saveTransfer(Transfer transfer);
}
