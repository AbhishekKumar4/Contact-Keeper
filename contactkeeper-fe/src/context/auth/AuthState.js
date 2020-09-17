import React, { useReducer } from 'react';
import AuthContext from './authContext';
import authReducer from './authReducer';
import axios from "axios";
import setAuthToken from '../../utils/setAuthToken'

import {
    REGISTER_SUCCESS,
    REGISTER_FAIL,
    USER_LOADED,
    AUTH_ERROR,
    LOGIN_SUCCESS,
    LOGIN_FAIL,
    LOGOUT,
    CLEAR_ERRORS
} from '../types';

const AuthState = props => {
    const initialState = {
       token : localStorage.getItem('Authorization'),
       isAuthenticated : null,
       loading : true,
       user : null,
       message : null,
       code : null
    };

    const [state, dispatch] = useReducer(authReducer, initialState);

    // Load User
    const loadUser = async () => {    
        if(localStorage.Authorization) {
            setAuthToken(localStorage.Authorization);
        }
        
        const config = {
            headers : {
                'Authorization': localStorage.Authorization
            }
        }

        try {
            const response = await axios.get('http://localhost:8080/auth', config);
            dispatch({
                type: USER_LOADED,
                payload: response.data
            });
        } catch(error) {
            dispatch({
                type: AUTH_ERROR
            });
        }
    }

    // Register User
    const register = async formData => {
        const config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

        try {
            const response = await axios.post('http://localhost:8080/register', formData, config);
            dispatch({
                type: REGISTER_SUCCESS,
                payload : { body : response.data, responseCode : response.status}
            });
        } catch (error) {
            dispatch({
                type : REGISTER_FAIL,
                payload: { body : error.response.data.message , responseCode : error.response.status}
            });
        }
    }

    // Login User
    const loginUser = async formData => {
        const config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }
        try {
            const response = await axios.post('http://localhost:8080/login', formData, config);
            console.log("This is response" + response);
            dispatch({
                type: LOGIN_SUCCESS,
                payload : { body : response.data.token, responseCode : response.status}
            });
            //loadUser();
        } catch (error) {
            dispatch({
                type : LOGIN_FAIL,
                payload: { body : error.response.data.message , responseCode : error.response.status}
            });
        }
        console.log("Login User");
    }

    // Logout
    const logout = () => dispatch({ type : LOGOUT});

    // Clear Errors
    const clearErrors = () => dispatch({ type : CLEAR_ERRORS});

    return (
        <AuthContext.Provider
        value = {{
            token : state.token,
            isAuthenticated : state.isAuthenticated,
            loading : state.loading,
            user : state.user,
            message : state.message,
            code : state.code,
            register,
            loadUser,
            loginUser,
            logout,
            clearErrors 
        }}
        >
            {props.children}
        </AuthContext.Provider>
    );
};

export default AuthState;