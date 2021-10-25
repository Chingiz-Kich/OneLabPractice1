package com.example.onelabpractice1.dao;

import com.example.onelabpractice1.models.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);
    List<User> getAll();
    User getByPhoneNumber(String phoneNumber);
}

