import React, { Component, PropTypes } from 'react';
import { Row, Col } from 'antd';
import { Router, Route, Link } from 'react-router';
import SideBar from './SideBar.jsx'

const Content = ({children}) => {
  return (
    <div>
      <Row>
        <Col span={6}>
          <SideBar/>
        </Col>
        <Col span={12} offset={3}>
          {children}
        </Col>
      </Row>
    </div>
  );
};

Content.propTypes = {
};

export default Content;