import React, {  useState, useContext, useEffect } from 'react'
import AuthContext from '../../context/auth/authContext'
import AlertContext from '../../context/alert/alertContext'

const Login = props => {

    const alertContext = useContext(AlertContext);
    const authContext = useContext(AuthContext);

    const { setAlert }  = alertContext;
    const { loginUser, message , code, clearErrors} = authContext;

    useEffect(() => {
        if(code === 409) {
            setAlert(message, 'danger');
            clearErrors();
        } else if (code === 200) {
            setAlert(message, 'success');
            props.history.push('/');
            clearErrors();
        }
    }, [message]);


    const [user, setUser] = useState({
        username: '',
        password: ''
    });

    const { username, password }   = user;

    const onChange = e => setUser({...user, [e.target.name]: e.target.value});

    const onSubmit = e => {
        e.preventDefault();
        if(username === '' && password === '') {
            setAlert('Please fill in all fields', 'danger');
        } else {
            loginUser({
                username,
                password
            });
        }
    }

    return (
        <div className = "form-container">
            <h1>
                Account <span className = "text-primary">Login</span>
            </h1>
            <form onSubmit = {onSubmit}>
                <div className = "form-group">
                    <label htmlFor = "name">Username</label>
                    <input type = "text" name = "username" value = {username} onChange = {onChange}></input>
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
