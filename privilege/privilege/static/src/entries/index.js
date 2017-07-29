import './index.html';
import ReactDOM from 'react-dom';
import React from 'react';
import { Router, Route, hashHistory,IndexRedirect } from 'react-router';
import HomePage from '../components/HomePage/HomePage.jsx'

import Main from '../layouts/Main';
import NotFound from '../components/common/NotFound';

ReactDOM.render(<Router history={hashHistory}>
    <Route path="/" spa="index" component={Main}>
      <IndexRedirect from="" to="home" />
    	<Route path="home" component={HomePage}>
      </Route>
      
      <Route path="*" component={NotFound} />
    </Route>
  </Router>, document.getElementById('root'));
