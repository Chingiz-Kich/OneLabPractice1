package com.example.onelabpractice1.security;

import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.security.jwt.JwtUser;
import com.example.onelabpractice1.security.jwt.JwtUserFactory;
import com.example.onelabpractice1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userService.getByPhoneNumber(phoneNumber);

        if (user == null) {
            throw new UsernameNotFoundException("User with phone number: " + phoneNumber + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", phoneNumber);
        return jwtUser;
    }
}