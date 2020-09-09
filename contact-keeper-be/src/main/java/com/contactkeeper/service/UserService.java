package com.contactkeeper.service;

import com.contactkeeper.entity.User;
import com.contactkeeper.exception.UserAlreadyExistException;
import com.contactkeeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        User loadedUser = userRepository.findByEmail(user.getEmail());
        if( loadedUser != null ) {
            throw new UserAlreadyExistException("Email id already exists!!!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User loadUser() {
        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByName(user);
    }

}
