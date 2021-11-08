package com.example.onelabpractice1.repository;

import com.example.onelabpractice1.models.Card;
import com.example.onelabpractice1.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    User getByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndPassword(String phoneNumber, String password);

    User findUserByPhoneNumberAndPassword(String phoneNumber, String password);

    List<User> findAll();
}
