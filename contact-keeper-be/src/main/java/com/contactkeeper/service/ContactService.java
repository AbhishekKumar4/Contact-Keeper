package com.contactkeeper.service;


import com.contactkeeper.entity.Contact;
import com.contactkeeper.exception.ContactNotFoundException;
import com.contactkeeper.exception.UserNotAuthorizedException;
import com.contactkeeper.repository.ContactRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact addContact(Contact contact) {
        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        contact.setUser(user);
        log.info("Saving a new contact for user %s", user);
        return contactRepository.save(contact);
    }

    public List<Contact> getContacts() {
        String user = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Getting all contacts for user %s", user);
        return contactRepository.findAllByUser(user);
    }

    public Contact updateContact(Contact contact) {
        log.info("Updating contact %s", contact.getId());
        String user = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Updating contact for user %s", user);
        Optional<Contact> contactOptional = contactRepository.findById(contact.getId());
        if(!contactOptional.isPresent()) {
            log.info("Contact with Id %s not Found", contact.getId());
            throw new ContactNotFoundException("Contact Not Found!!!");
        }
        //Need to change this implementation and implement it using user id's
        Contact contactEntity = contactOptional.get();
        if(contactEntity.getName().equalsIgnoreCase(user)) {
            throw new UserNotAuthorizedException("User not authorized!!!");
        }
        //
        return contactRepository.save(contact);
    }

    public Contact deleteContact(Long contactId) {
        log.info("Deleting contact with Id %s", contactId);
        String user = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Contact> contactOptional = contactRepository.findById(contactId);
        if(!contactOptional.isPresent()) {
            log.info("Contact with Id %s not Found", contactId);
            throw new ContactNotFoundException("Contact Not Found!!!");
        }
        Contact contactEntity = contactOptional.get();
        if(!contactEntity.getName().equalsIgnoreCase(user)) {
            throw new UserNotAuthorizedException("User not authorized!!!");
        }
        contactRepository.deleteById(contactId);
        return contactEntity;
    }

}
