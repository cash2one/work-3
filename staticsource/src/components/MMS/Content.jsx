import React, { Component, PropTypes } from 'react';
import { Row, Col } from 'antd';
import { Router, Route, Link } from 'react-router';
import Sider from './Sider.jsx';
import styles from './Page.less';

const Content = ({children}) => {
  return (
   <div className={styles.contents}>
      <div className={styles.Page_left}>
          <Sider/>
      </div>
      <div className={styles.Page_right}>
        <div className={styles.topBG}></div>
        <div className={styles.rightCon}>
          {children}
        </div>
      </div>
    </div>
  );
};
Content.propTypes = {
};

export default Content;
