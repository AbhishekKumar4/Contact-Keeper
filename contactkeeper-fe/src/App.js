import React, {Fragment} from 'react';
import './App.css';
import NavBar from './components/layout/NavBar'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import Home from './components/pages/Home'
import About from './components/pages/About'

const App = () => {
  return (
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
  );
}

export default App;
