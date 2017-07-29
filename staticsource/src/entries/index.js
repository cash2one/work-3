import './index.html';
import ReactDOM from 'react-dom';
import React from 'react';
import { Router, Route, hashHistory,IndexRedirect } from 'react-router';
import Content from '../components/MMS/Content.jsx';


import index from '../components/MMS/User_Management/index.jsx';
import systemCont from '../components/MMS/User_Management/systemCont.jsx';
import menuCont from '../components/MMS/User_Management/menuCont.jsx';
import pageCont from '../components/MMS/User_Management/pageCont.jsx';
import roleCont from '../components/MMS/User_Management/roleCont.jsx';
import userGroupCont from '../components/MMS/User_Management/userGroupCont.jsx';
import userCont from '../components/MMS/User_Management/userCont.jsx';
import logCont from '../components/MMS/User_Management/logCont.jsx';
import upload from '../components/MMS/User_Management/uploadCont.jsx';

import Main from '../layouts/Main';
import NotFound from '../components/MMS/common/NotFound';

ReactDOM.render(
<Router history={hashHistory}>
  <Route path="/" component={Main}>
    <IndexRedirect from="" to="content/index" />
      <Route path="content" component={Content} >
        <Route path="index" component={index} />
        <Route path="systemCont" component={systemCont} />
        <Route path="menuCont" component={menuCont} />
        <Route path="pageCont" component={pageCont} />
        <Route path="roleCont" component={roleCont} />
        <Route path="userGroupCont" component={userGroupCont} />
        <Route path="userCont" component={userCont} />
        <Route path="logCont" component={logCont} />
        <Route path="upload" component={upload} />
      </Route>
      <Route path="*" component={NotFound} />
  </Route>
</Router>, document.getElementById('root'));
