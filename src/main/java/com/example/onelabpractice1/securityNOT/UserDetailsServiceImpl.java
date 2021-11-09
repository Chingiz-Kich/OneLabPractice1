/*
package com.example.onelabpractice1.securityNOT;

import com.example.onelabpractice1.models.User;
import com.example.onelabpractice1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsls loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByPhoneNumber(phoneNumber);
            return SecurityUser.fromUser(user);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User does not exist");
        }
    }
}
*/
