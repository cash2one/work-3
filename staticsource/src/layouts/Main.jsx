import React, { Component, PropTypes } from 'react';
import { Router, Route} from 'react-router';
import {Row, Col} from 'antd';
import '../components/MMS/common/common.less';

const Main = (props) => {
  let { children } = props;
  return (
    <div style={{position:'relative'}}>
      <div >
      	{children}
      </div>
    </div>
  );
};


export default Main;
