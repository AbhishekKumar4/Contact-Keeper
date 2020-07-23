package com.contactkeeper.service;


import com.contactkeeper.entity.Contact;
import com.contactkeeper.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact addContact(Contact contact) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        contact.setUser(user.getUsername());
        return contactRepository.save(contact);
    }

    public List<Contact> getContacts() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return contactRepository.findAllByUser(user.getUsername());
    }

}
