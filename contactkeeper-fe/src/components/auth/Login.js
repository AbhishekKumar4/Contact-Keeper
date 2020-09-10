import React, {  useState, useContext, useEffect } from 'react'
import AuthContext from '../../context/auth/authContext'
import AlertContext from '../../context/alert/alertContext'

const Login = () => {

    const alertContext = useContext(AlertContext);
    const authContext = useContext(AuthContext);

    const { setAlert }  = alertContext;
    const { loginUser, message , code, clearErrors} = authContext;

    useEffect(() => {
        if(code === 409) {
            setAlert(message, 'danger');
            clearErrors();
        } else if (code === 201) {
            setAlert(message, 'success');
            props.history.push('/login');
            clearErrors();
        }
    }, [message]);


    const [user, setUser] = useState({
        email: '',
        password: ''
    });

    const { email, password }   = user;

    const onChange = e => setUser({...user, [e.target.name]: e.target.value});

    const onSubmit = e => {
        e.preventDefault();
        if(email === '' && password === '') {
            setAlert('Please fill in all fields', 'danger');
        }
    }

    return (
        <div className = "form-container">
            <h1>
                Account <span className = "text-primary">Login</span>
            </h1>
            <form onSubmit = {onSubmit}>
                <div className = "form-group">
                    <label htmlFor = "name">Email Address</label>
                    <input type = "email" name = "email" value = {email} onChange = {onChange}></input>
                </div>
                <div className = "form-group">
                    <label htmlFor = "password">Password</label>
                    <input type = "password" name = "password" value = {password} onChange = {onChange}></input>
                </div>
                <input type = "submit" value = "Login" className = "btn btn-primary btn-block"></input>
            </form>
        </div>
    )
}

export default Login;
