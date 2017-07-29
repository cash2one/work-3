import React from 'react';
import {Link} from 'react-router';
import {Row, Col} from 'antd';
import styles from './Header.less';
import Nav from './Nav';

const Header = ({ pathname, spa }) => {
  console.log(spa);
  return (
    <div className={styles.head}>
      <Row>
        <Col span={6}>
          <h1>信美相互人寿</h1>
        </Col>
        <Col span={18}>
          <Nav pathname={pathname} spa={spa} />
        </Col>
      </Row>
    </div>
  );
};

export default Header;
