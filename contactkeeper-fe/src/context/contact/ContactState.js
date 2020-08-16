import React, { useReducer } from 'react';
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
} from '../types';

const ContactState = props => {
    const initialState = {
        contacts: [
            {
                id :1,
                name : 'Abhishek Kumar',
                email : 'abc@gmail.com',
                phone : "125478965",
                type : 'professional'
            },
            {
                id :2,
                name : 'Ruchir Saxena',
                email : 'abc@gmail.com',
                phone : "125478965",
                type : 'personal'
            },
            {
                id :3,
                name : 'Manmeet Singh',
                email : 'abc@gmail.com',
                phone : "125478965",
                type : 'personal'
            }
        ],
        current : null
    };

    const [state, dispatch] = useReducer(ContactReducer, initialState);

    // Add Contact

    const addContact = contact => {
        dispatch({type: ADD_CONTACT, payload: contact});
    }

    // Delete Contact
    const deleteContact = id => {
        dispatch({type: DELETE_CONTACT, payload: id});
    }

    // Set Current Contact
    const setCurrent = contact => {
        dispatch({type: SET_CURRENT, payload: contact});
    }

    // Clear current contact
    const clearCurrent = () => {
        dispatch({type: CLEAR_CURRENT});
    }

    // Update Contact

    // Filter Contact

    // Clear Filter

    return (
        <ContactContext.Provider
        value = {{
            contacts : state.contacts,
            current: state.current,
            addContact,
            deleteContact,
            setCurrent,
            clearCurrent
        }}
        >
            {props.children}
        </ContactContext.Provider>
    );
};

export default ContactState;