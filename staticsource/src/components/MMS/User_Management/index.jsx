import React, { Component, PropTypes } from 'react';
import { Form, Icon } from 'antd';
import { Router, Route, IndexRoute, Link } from 'react-router';

const createForm = Form.create;

let Demo = React.createClass({
  render() {
    return (
      <div style={{textAlign:"center",marginTop:"20%"}}>
        <Icon type="windows"style={{fontSize:"40px",marginBottom:"20px"}} />
        <p style={{fontSize:"40px"}}>欢迎登录权限管理系统</p>
      </div>
    );
  }
});

Demo = createForm()(Demo);
export default Demo;
