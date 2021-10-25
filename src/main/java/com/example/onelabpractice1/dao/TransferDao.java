package com.example.onelabpractice1.dao;

import com.example.onelabpractice1.models.Transfer;

import java.time.LocalDate;
import java.util.List;

public interface TransferDao {
    void saveTransfer(Transfer transfer);
    List<Transfer> getAll();
}
