import React, { useReducer } from 'react';
import AuthContext from './authContext';
import authReducer from './authReducer';
import axios from "axios";
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
       token : localStorage.getItem('token'),
       isAuthenticated : null,
       loading : true,
       user : null,
       error : null,
       code : null
    };

    const [state, dispatch] = useReducer(authReducer, initialState);

    // Load User
    const loadUser = () => {
        console.log("Load User");
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
            console.log(response);
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
    const loginUser = () => {
        console.log("Login User");
    }

    // Logout
    const logoutUser = () => {
        console.log("Logout User");
    }

    // Clear Errors
    const clearErrors = () => dispatch({ type : CLEAR_ERRORS});

    return (
        <AuthContext.Provider
        value = {{
            token : state.token,
            isAuthenticated : state.isAuthenticateden,
            loading : state.loading,
            user : state.user,
            error : state.error,
            code : state.code,
            register,
            loadUser,
            loginUser,
            logoutUser,
            clearErrors 
        }}
        >
            {props.children}
        </AuthContext.Provider>
    );
};

export default AuthState;