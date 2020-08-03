import React, { userReducer } from 'react';
import uuid from 'uuid';
import ContactContext from './contactContext';
import ContactReducer from './contactReducer';
import {
    ADD_CONTACT,
    DELETE_CONTACT,
    SET_CURRENT,
    CLEAR_CURRENT,
    UPDATE_CONTACT,
    FILTER_CONTACTS,
    CLEAR_FILTER
} from '../types'

const ContactState = props => {
    const initialState = {
        contacts :[
            {
                id :1,
                name : 'Abhishek Kumar',
                email : 'abc@gmail.com',
                type : 'personal'
            },
            {
                id :2,
                name : 'Ruchir Saxena',
                email : 'abc@gmail.com',
                type : 'personal'
            },
            {
                id :3,
                name : 'Manmeet Singh',
                email : 'abc@gmail.com',
                type : 'personal'
            }
        ]
    };

    const [state, dispatch] = userReducer(ContactReducer, initialState);

    // Add Contact

    // Delete Contact

    // Set Current Contact

    // Clear current contact

    // Update Contact

    // Filter Contact

    // Clear Filter

    return (
        <ContactContext.Provider
        value = {{
            contacts : state.contacts;
        }}
        >
            {props.children}
        </ContactContext.Provider>
    );
};

export default ContactState;