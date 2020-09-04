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
            debugger
            dispatch({
                action: REGISTER_SUCCESS,
                payload : response.data
            });
        } catch (error) {
            dispatch({
                action : REGISTER_FAIL,
                payload: "Register failed"
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
    const clearErrors = () => {
        console.log("Clear Errors");
    }

    return (
        <AuthContext.Provider
        value = {{
            token : state.token,
            isAuthenticated : state.isAuthenticateden,
            loading : state.loading,
            user : state.user,
            error : state.error,
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