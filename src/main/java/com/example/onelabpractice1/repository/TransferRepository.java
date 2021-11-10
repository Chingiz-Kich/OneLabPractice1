package com.example.onelabpractice1.repository;

import com.example.onelabpractice1.models.Transfer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface TransferRepository extends CrudRepository<Transfer, Long> {
    @NonNull
    List<Transfer> findAll();
}
