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
        case REGISTER_FAIL:
            return {
                ...state,
                token: localStorage.removeItem('token'),
                isAuthenticated: false,
                loading: false,
                user: null,
                message: action.payload.body,
                code : action.payload.responseCode
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