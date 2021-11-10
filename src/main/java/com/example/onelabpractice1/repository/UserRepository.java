package com.example.onelabpractice1.repository;

import com.example.onelabpractice1.models.Role;
import com.example.onelabpractice1.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndPassword(String phoneNumber, String password);

    User findUserByPhoneNumberAndPassword(String phoneNumber, String password);

    @NonNull
    List<User> findAll();

    User findByPhoneNumberAndRole(String phoneNumber, Role role);
}
