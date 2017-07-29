import React, { Component, PropTypes } from 'react';
import { Router, Route} from 'react-router';
import {Row, Col} from 'antd';
import Header from '../components/common/Header';
import Footer from '../components/common/Footer';
import '../components/common/common.less';

const Main = (props) => {
  let { children, location={}, route } = props;
  console.log(props);

  return (
    <div style={{position:'relative'}}>
      <Header {...location} spa={route.spa}/>
      <div >
      	{children}
      </div>
      
      <Footer />
    </div>

  );
};


export default Main;
