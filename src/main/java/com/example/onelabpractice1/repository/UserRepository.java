package com.example.onelabpractice1.repository;

import com.example.onelabpractice1.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByPhoneNumber(String phoneNumber);

    boolean existsUserByPhoneNumber(String phoneNumber);

    User getByPhoneNumber(String phoneNumber);

    List<User> findAll();
}
