package com.example.onelabpractice1.repository;

import com.example.onelabpractice1.dao.Impl.UserDaoImpl;
import com.example.onelabpractice1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo implements IUserRepo{

    private UserDaoImpl userDaoImpl;

    @Autowired UserRepo(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

     @Override
     public void saveUser(User user) {
        userDaoImpl.saveUser(user);
     }

     @Override
     public List<User> getAll() {
         return userDaoImpl.getAll();
     }

     @Override
     public boolean existByPhoneNumber(String phoneNumber) {
         return userDaoImpl.getByPhoneNumber(phoneNumber) != null;
     }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
         return userDaoImpl.getByPhoneNumber(phoneNumber);
    }
}
