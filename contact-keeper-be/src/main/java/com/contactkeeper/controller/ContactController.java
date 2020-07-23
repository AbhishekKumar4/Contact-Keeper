package com.contactkeeper.controller;

import com.contactkeeper.entity.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactController {

    @PostMapping(path = "/contact")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(path = "/contact")
    public ResponseEntity<List<Contact>> getContacts() {
        //UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
