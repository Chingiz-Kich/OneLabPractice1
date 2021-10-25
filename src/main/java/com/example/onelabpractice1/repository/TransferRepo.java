package com.example.onelabpractice1.repository;

import com.example.onelabpractice1.dao.Impl.TransferDaoImpl;
import com.example.onelabpractice1.models.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransferRepo implements ITransferRepo{

    private TransferDaoImpl transferDaoImpl;

    @Autowired
    public TransferRepo(TransferDaoImpl transferDaoImpl) {
        this.transferDaoImpl = transferDaoImpl;
    }

    @Override
    public List<Transfer> getHistoryList() {
        return transferDaoImpl.getAll();
    }

    @Override
    public void saveTransfer(Transfer transfer) {
        transferDaoImpl.saveTransfer(transfer);
    }
}
