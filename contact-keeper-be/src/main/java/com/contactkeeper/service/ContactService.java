package com.contactkeeper.service;


import com.contactkeeper.entity.Contact;
import com.contactkeeper.exception.ContactNotFoundException;
import com.contactkeeper.exception.UserNotAuthorizedException;
import com.contactkeeper.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact addContact(Contact contact) {
        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        contact.setUser(user);
        return contactRepository.save(contact);
    }

    public List<Contact> getContacts() {
        String user = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return contactRepository.findAllByUser(user);
    }

    public Contact updateContact(Contact contact) {
        String user = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Contact> contactOptional = contactRepository.findById(contact.getId());
        if(!contactOptional.isPresent()) {
            throw new ContactNotFoundException("Contact Not Found!!!");
        }
        Contact contactEntity = contactOptional.get();
        if(contactEntity.getName().equalsIgnoreCase(user)) {
            throw new UserNotAuthorizedException("User not authorized!!!");
        }
        return contactRepository.save(contact);
    }

    public Contact deleteContact(Long contactId) {
        String user = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Contact> contactOptional = contactRepository.findById(contactId);
        if(!contactOptional.isPresent()) {
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
