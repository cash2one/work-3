import React from 'react';
import styles from './Footer.less';
import { Row, Col } from 'antd';

const Footer = () => {
  return (
    <div className={styles.foot}>
      <Row>
	      <Col span={3}></Col>
	      <Col span={7}>
	      	<p>关于我们     丨     精英风采    丨     网站地图</p>
	      </Col>
	      <Col span={7}>联系我们 </Col>
	      <Col span={4}>关注我们</Col>
	      <Col span={3}></Col>
	    </Row>
    </div>
  );
};

export default Footer;
