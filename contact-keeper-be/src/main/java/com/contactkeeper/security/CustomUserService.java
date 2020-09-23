package com.contactkeeper.security;

import com.contactkeeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.contactkeeper.entity.User user =  userRepository.findByName(userName);
        UserDetails loadedUser = User.builder().username(user.getName())
                .password(user.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .disabled(false)
                .authorities(new ArrayList<>()).build();
         return loadedUser;
    }
}
