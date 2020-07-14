package com.contactkeeper.controller;

import com.contactkeeper.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactKeeperController {

    @GetMapping
    public String sayHello() {
        return "hello";
    }

    @PostMapping(path = "/contact/user")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        System.out.println(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
