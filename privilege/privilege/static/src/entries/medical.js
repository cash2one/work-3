
import './medical.html';
import ReactDOM from 'react-dom';
import React from 'react';
import { Router, Route, hashHistory } from 'react-router';

import Main from '../layouts/Main';
import NotFound from '../components/common/NotFound';

ReactDOM.render(<Router history={hashHistory}>
    <Route path="/" spa="medical" component={Main}>
     	<div>医疗服务</div>
      <Route path="*" component={NotFound} />
    </Route>
  </Router>, document.getElementById('root'));
