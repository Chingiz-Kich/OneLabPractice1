package com.example.onelabpractice1.repository;

import com.example.onelabpractice1.models.User;

import java.util.List;

public interface IUserRepo {
    void saveUser(User user);

    List<User> getAll();

    boolean existByPhoneNumber(String phoneNumber);

    User getByPhoneNumber(String phoneNumber);
}
