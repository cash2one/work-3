import './login.html';
import ReactDOM from 'react-dom';
import React from 'react';
import { Router, Route, hashHistory,IndexRoute,IndexRedirect } from 'react-router';


import Main from '../layouts/Main';
import NotFound from '../components/common/NotFound';

ReactDOM.render(<Main><div>
  登陆
  </div></Main>, document.getElementById('root'));
