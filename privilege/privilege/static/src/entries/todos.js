import './todos.html';
import ReactDOM from 'react-dom';
import React from 'react';
import { Router, Route, hashHistory,IndexRedirect } from 'react-router';

import Main from '../layouts/Main';
import NotFound from '../components/common/NotFound';
import TodosPage from '../components/Todos/Page';
import Todos from '../components/Todos/Todos';

ReactDOM.render(<Router history={hashHistory}>
    <Route path="/" component={Main}>
      <IndexRedirect from="" to="todos" />
      <Route path="todos" component={TodosPage}>
        <IndexRedirect from="" to="actived" />
        <Route path="actived" component={Todos} />
        <Route path="completed" component={Todos} />
      </Route>
      <Route path="*" component={NotFound} />
    </Route>
  </Router>, document.getElementById('root'));
