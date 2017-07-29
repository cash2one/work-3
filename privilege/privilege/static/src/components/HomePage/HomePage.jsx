import React, { Component, PropTypes } from 'react';
import { Row, Col ,Button } from 'antd';
import { Router, Route, Link } from 'react-router';
import styles from './HomePage.less'

const HomePage = () => {
  return (
    <div>
      <div className={styles["wrap-top"]}>
        <div className={styles.title}>
          <Row >
            <Col span={12} >
              产品推荐
            </Col>
            <Col span={12}>
              <a href='/insure.html'>更多产品</a>
            </Col>
          </Row>
        </div>
        <div className={styles.product}>
          <Row type="flex" justify="space-around" >
            <Col span={4}>
              <div className={styles.border}>
               <Button type="primary">立即投保</Button>
              </div>
            </Col>
            <Col span={4}>
              <div className={styles.border}>
               <Button type="primary">立即投保</Button>
              </div>
            </Col>
            <Col span={4}>
              <div className={styles.border}>
               <Button type="primary">立即投保</Button>
              </div>
            </Col>
            <Col span={4}>
              <div className={styles.border}>
               <Button type="primary">立即投保</Button>
              </div>
            </Col>
          </Row>
          <p>没有想要的产品？快来来试试智能保险顾问</p>
        </div>
      </div>
      <div className={styles['wrap-bottom']}>
        <Row type="flex" justify="space-around">
          <Col span={6} >
            <div className={styles["wrap-border"]}>
              <Row>
                <div className={styles.left}>
                <Col span={24} offset={3}>保单服务</Col>
                </div>
              </Row>
              <Row>
                <div className={styles.bottom}>
                  <Col span={8}>我的保单</Col>
                  <Col span={8}>理赔报案</Col>
                  <Col span={8}>资料提交</Col>
                </div>
              </Row>
            </div>
          </Col>
          <Col span={6}>
            <div className={styles["wrap-border"]}>
              <Row>
                <div className={styles.left}>
                <Col span={24} offset={3}>共创计划</Col>
                </div>
              </Row>
              <Row>
                 <div className={styles.bottom}>
                  <Col span={8}>我的保单</Col>
                  <Col span={8}>理赔报案</Col>
                  <Col span={8}>资料提交</Col>
                </div>
            </Row>
            </div>
          </Col>
          <Col span={6}>
            <div className={styles["wrap-border"]}>
              <Row>
                <div className={styles.left}>
                <Col span={24} offset={3}>共创计划</Col>
                </div>
              </Row>
              <Row>
                 <div className={styles.bottom}>
                  <Col span={8}>我的保单</Col>
                  <Col span={8}>理赔报案</Col>
                  <Col span={8}>资料提交</Col>
                </div>
            </Row>
            </div>
          </Col>
        </Row>

      </div>
      
    </div>
    
  );
};

HomePage.propTypes = {
};

export default HomePage;
