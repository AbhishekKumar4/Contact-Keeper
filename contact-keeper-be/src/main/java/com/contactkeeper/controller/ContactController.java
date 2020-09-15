package com.contactkeeper.controller;

import com.contactkeeper.entity.Contact;
import com.contactkeeper.service.ContactService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Log4j2
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping(path = "/contact")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        return new ResponseEntity<>(contactService.addContact(contact), HttpStatus.OK);
    }

    @GetMapping(path = "/contact")
    public ResponseEntity<List<Contact>> getContacts() {
        return new ResponseEntity<>(contactService.getContacts(), HttpStatus.OK);
    }

    @PutMapping(path = "/contact")
    public ResponseEntity<Contact> updateContacts(@RequestBody Contact contact) {
        return new ResponseEntity<>(contactService.updateContact(contact), HttpStatus.OK);
    }

    @DeleteMapping(path = "/contact/{id}")
    public ResponseEntity<Contact> deleteContacts(@PathVariable Long id) {
        return new ResponseEntity<>(contactService.deleteContact(id), HttpStatus.OK);
    }


}
