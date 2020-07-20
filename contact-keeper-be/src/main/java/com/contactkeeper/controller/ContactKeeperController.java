package com.contactkeeper.controller;

import com.contactkeeper.entity.User;
import com.contactkeeper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ContactKeeperController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String test() {
        return "hello";
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.OK);
    }

}
