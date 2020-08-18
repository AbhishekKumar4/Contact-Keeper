import React, {Fragment} from 'react';
import './App.css';
import NavBar from './components/layout/NavBar'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import Home from './components/pages/Home'
import About from './components/pages/About'
import ContactState from './context/contact/ContactState'
import AuthState from './context/auth/AuthState'

const App = () => {
  return (
    <AuthState>
    <ContactState>
    <Router>
      <Fragment>
        <NavBar />
          <div className = 'container'>
            <Switch>
              <Route exact path='/' component= {Home}></Route>
              <Route exact path='/about' component= {About}></Route>
            </Switch>
          </div>
      </Fragment>
    </Router>
    </ContactState>
    </AuthState>
  );
}

export default App;
