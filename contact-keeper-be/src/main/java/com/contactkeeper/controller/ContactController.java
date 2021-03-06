package com.contactkeeper.controller;

import com.contactkeeper.entity.Contact;
import com.contactkeeper.service.ContactService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@Log4j2
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping(path = "/contact")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact, Principal principal) {
        String user = principal.getName();
        return new ResponseEntity<>(contactService.addContact(contact, user), HttpStatus.OK);
    }

    @GetMapping(path = "/contact")
    public ResponseEntity<List<Contact>> getContacts(Principal principal) {
        String user = principal.getName();
        return new ResponseEntity<>(contactService.getContacts(user), HttpStatus.OK);
    }

    @PutMapping(path = "/contact")
    public ResponseEntity<Contact> updateContacts(@RequestBody Contact contact, Principal principal) {
        String user = principal.getName();
        return new ResponseEntity<>(contactService.updateContact(contact, user), HttpStatus.OK);
    }

    @DeleteMapping(path = "/contact/{id}")
    public ResponseEntity<Contact> deleteContacts(@PathVariable Long id, Principal principal) {
        String user = principal.getName();
        return new ResponseEntity<>(contactService.deleteContact(id, user), HttpStatus.OK);
    }
}
