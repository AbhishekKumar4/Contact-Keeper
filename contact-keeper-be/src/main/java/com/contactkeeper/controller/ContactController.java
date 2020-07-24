package com.contactkeeper.controller;

import com.contactkeeper.entity.Contact;
import com.contactkeeper.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
    public ResponseEntity<Contact> updateContacts(@RequestBody Contact contact) throws Exception {
        return new ResponseEntity<>(contactService.updateContact(contact), HttpStatus.OK);
    }

    @DeleteMapping(path = "/contact/{id}")
    public ResponseEntity<Contact> deleteContacts(@PathVariable Long contactId) throws Exception {
        return new ResponseEntity<>(contactService.deleteContact(contactId), HttpStatus.OK);
    }


}
