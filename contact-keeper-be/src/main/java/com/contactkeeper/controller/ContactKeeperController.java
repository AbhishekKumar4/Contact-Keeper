package com.contactkeeper.controller;

import com.contactkeeper.entity.User;
import com.contactkeeper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class ContactKeeperController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String test() {
        return "hello";
    }

/*    @GetMapping(path = "/contact/user/{name}")
    public ResponseEntity<User> getUser(@PathVariable("name") String name) {
        return new ResponseEntity<>(userService.getUser(name), HttpStatus.OK);
    }*/

    @PostMapping(value = "/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.OK);
    }

}
