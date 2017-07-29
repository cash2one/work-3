import './insure.html';
import ReactDOM from 'react-dom';
import React from 'react';
import { Router, Route, hashHistory,IndexRedirect } from 'react-router';
import Content from '../components/PersonalInsurance/Content.jsx'
import Item1 from '../components/PersonalInsurance/Item1.jsx'
import Form from '../components/PersonalInsurance/Form.jsx'


import Main from '../layouts/Main';
import NotFound from '../components/common/NotFound';

ReactDOM.render(<Router history={hashHistory}>
    <Route path="/" component={Main}>
      <IndexRedirect from="" to="content" />
      
      <Route path="content" component={Content} >
        <IndexRedirect from="" to="item1" />
         <Route path="item1" component={Item1} />
         <Route path="item2" component={Form} />
      </Route>
      <Route path="*" component={NotFound} />
    </Route>
  </Router>, document.getElementById('root'));
