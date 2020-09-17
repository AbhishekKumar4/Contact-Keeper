import {
    REGISTER_SUCCESS,
    REGISTER_FAIL,
    USER_LOADED,
    AUTH_ERROR,
    LOGIN_SUCCESS,
    LOGIN_FAIL,
    LOGOUT,
    CLEAR_ERRORS
} from '../types'

export default (state, action) => {
    switch(action.type) {
        case USER_LOADED :
            return {
                ...state,
                isAuthenticated: true,
                loading: false,
                user : action.payload
            }
        case REGISTER_SUCCESS:
            return {
                 ...state,
                 token: null,
                 isAuthenticated: false,
                 loading: false,
                 user: null,
                 message:'User Registered Successfully !!!',
                 code : action.payload.responseCode
            }
        case LOGIN_SUCCESS:
                localStorage.setItem('Authorization', action.payload.body);
                return {
                     ...state,
                     ...action.payload.body,
                     isAuthenticated: true,
                     loading: false,
                     user: null,
                     message:'Logged In Successfully !!!',
                     code : action.payload.responseCode
            }
        case REGISTER_FAIL:
        case LOGIN_FAIL:
        case LOGOUT:
            localStorage.removeItem('Authorization')
            return {
                ...state,
                token: null,
                isAuthenticated: false,
                loading: false,
                user: null,
                message: "User Logged out!!!",
                code : null
            }
        case AUTH_ERROR:
            localStorage.removeItem('Authorization');
            return {
                ...state,
                token: null,
                isAuthenticated: false,
                loading: false,
                user: null,
                message: "Auth Error!!!",
                code : null
            }
        case CLEAR_ERRORS:
            return {
                ...state,
                code: null,
                message: null
            }    
        default:
            return state;
    }
}