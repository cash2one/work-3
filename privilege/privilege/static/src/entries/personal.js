
import './personal.html';
import ReactDOM from 'react-dom';
import React from 'react';
import { Router, Route, hashHistory } from 'react-router';

import Main from '../layouts/Main';
import NotFound from '../components/common/NotFound';

ReactDOM.render(<Router history={hashHistory}>
    <Route path="/" component={Main}>
      <Route path="*" component={NotFound} />
    </Route>
  </Router>, document.getElementById('root'));
