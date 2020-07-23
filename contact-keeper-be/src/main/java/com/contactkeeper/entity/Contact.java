package com.contactkeeper.entity;

import javax.persistence.*;

@Entity(name = "Contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USER_ID")
    @ManyToOne
    private User user;
}
