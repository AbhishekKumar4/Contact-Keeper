package com.contactkeeper.service;

import com.contactkeeper.entity.User;
import com.contactkeeper.exception.UserAlreadyExistException;
import com.contactkeeper.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        log.info("Registering a new user {}", user.getEmail());
        User loadedUser = userRepository.findByEmail(user.getEmail());
        if( loadedUser != null ) {
            log.info("User with id {} already exists!!!", user.getEmail());
            throw new UserAlreadyExistException("Email id already exists!!!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User loadUser() {
        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Loading user {}", user);
        User loadedUser =  userRepository.findByName(user);
        //Don't send password
        loadedUser.setPassword(null);
        return loadedUser;
    }

}
