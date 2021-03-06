package com.contactkeeper.controller;

import com.contactkeeper.entity.User;
import com.contactkeeper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    @GetMapping(value = "/auth")
    public ResponseEntity<User> loadUser() {
        return new ResponseEntity<>(userService.loadUser(), HttpStatus.OK);
    }

}
