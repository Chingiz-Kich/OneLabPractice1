package com.example.onelabpractice1.repository;

import com.example.onelabpractice1.models.Card;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, String> {
    Card findCardByNumber(String number);

    boolean existsCardByNumber(String number);

    List<Card> findAll();

    @Transactional
    @Modifying
    @Query(value = "UPDATE cards SET balance = (:money) WHERE number = (:cardNumber)", nativeQuery = true)
    void updateBalance(@Param("cardNumber") String cardNumber, @Param("money")double money);
}
