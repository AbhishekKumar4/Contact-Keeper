package com.contactkeeper.security;

import com.contactkeeper.repository.CustomUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private CustomUserRepo customUserRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.contactkeeper.entity.User user =  customUserRepo.findByName(userName);
        return new User(user.getName(), user.getPassword(), new ArrayList<>());
    }
}
