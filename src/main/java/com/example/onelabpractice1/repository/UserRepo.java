package com.example.onelabpractice1.repository;

import com.example.onelabpractice1.dto.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepo implements IUserRepo{
    Map<String, User> users = new HashMap<>();

     @Override
     public void saveUser(User user) {
        users.put(user.getPhoneNumber(), user);
     }

     @Override
     public List<User> getAll() {
         return new ArrayList<>(users.values());
     }

     @Override
     public boolean existByPhoneNumber(String phoneNumber) {
         return users.containsKey(phoneNumber);
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
         return users.get(phoneNumber);
    }
}
