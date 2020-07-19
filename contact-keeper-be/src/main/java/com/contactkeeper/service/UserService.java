package com.contactkeeper.service;

import com.contactkeeper.entity.User;
import com.contactkeeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

/*    @Autowired
    private PasswordEncoder passwordEncoder;*/

    public User registerUser(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
