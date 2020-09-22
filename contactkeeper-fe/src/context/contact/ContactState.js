import React, { useReducer } from 'react';
import ContactContext from './contactContext';
import ContactReducer from './contactReducer';
import axios from 'axios';
import {
    ADD_CONTACT,
    DELETE_CONTACT,
    SET_CURRENT,
    CLEAR_CURRENT,
    UPDATE_CONTACT,
    FILTER_CONTACTS,
    CLEAR_FILTER,
    CONTACT_ERROR,
    GET_CONTACTS,
    CLEAR_CONTACTS
} from '../types';

const ContactState = props => {
    const initialState = {
        contacts: null,
        current : null,
        filtered: null,
        error : null
    };

    const [state, dispatch] = useReducer(ContactReducer, initialState);

    // Get Contacts
    const getContacts = async () => {
        try {
            const response =  await axios.get('http://localhost:8080/contact')
            dispatch({type: GET_CONTACTS, payload: response.data});
        } catch (error) {
            dispatch({type : CONTACT_ERROR,
            payload : error.response.msg})
        }
    }


    // Add Contact
    const addContact = async contact => {

        const config = {
            headers : {
                'Context-Type' : 'application/json'
            }
        }
        try {
            const response =  await axios.post('http://localhost:8080/contact', contact, config)
            dispatch({type: ADD_CONTACT, payload: response.data});
        } catch (error) {
            dispatch({type : CONTACT_ERROR,
            payload : error.response.msg})
        }
    }

    // Delete Contact
    const deleteContact = id => {
        dispatch({type: DELETE_CONTACT, payload: id});
    }

    // Clear Contacts
    const clearContacts = () => {
        dispatch({type: CLEAR_CONTACTS});
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
    const updateContact = contact => {
        dispatch({type: UPDATE_CONTACT, payload: contact});
    }
    // Filter Contact
    const filterContact = text => {
        dispatch({type: FILTER_CONTACTS, payload: text});
    }
    // Clear Filter
    const clearFilter = () => {
        dispatch({type: CLEAR_FILTER});
    }

    return (
        <ContactContext.Provider
        value = {{
            contacts : state.contacts,
            current: state.current,
            filtered: state.filtered,
            error: state.error,
            addContact,
            updateContact,
            deleteContact,
            setCurrent,
            clearCurrent,
            filterContact,
            clearFilter,
            getContacts,
            clearContacts
        }}
        >
            {props.children}
        </ContactContext.Provider>
    );
};

export default ContactState;