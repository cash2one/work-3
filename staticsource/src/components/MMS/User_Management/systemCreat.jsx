import React, { Component, PropTypes } from 'react';
import {Radio, Button, Form, Input, message } from 'antd';
import { Router, Route, IndexRoute, Link } from 'react-router';
import { addSystem } from '../../../services/api.js';

const createForm = Form.create;
const FormItem = Form.Item;
const RadioGroup = Radio.Group;

let systemCreat = React.createClass({
  //	页面初始化
  getInitialState(){
    return {

    }
  },

  /*初始化表单*/
  componentDidMount() {
    this.props.form.resetFields();
  },

  /*新建系统资源*/
  addOk(e) {
    e.preventDefault();
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        console.log('Errors in form!!!');
        return;
      }
      let systemMsg={
        stateCd:values.addstatus,
        systemPlatformCd: values.addsystemNum,
        systemPlatformDesc:values.addremark,
        systemPlatformName:values.addsystemName
      };
      addSystem(systemMsg).then((msg)=>{
        if (!msg.jsonResult.success){
          message.error(msg.jsonResult.resultMsg);
        }else{
          message.success(msg.jsonResult.data);
          this.props.callback(msg);
          this.props.form.resetFields();
        }
      });
    });
  },
  addCancel() {
    this.setState({ addCust: false });
  },
  /*新建系统资源 END*/

  /*表单校验规则*/
  //新建时，校验用户组名规则
  systemExists(rule, value, callback) {
    if (!value) {
      callback();
    } else {
      let pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
      if (pattern.test(value)) {
        callback([new Error('请输入中文、英文或数字！')]);
      }else {
        callback();
      }
    }
  },
  systemNum(rule, value, callback) {
    if (!value) {
      callback();
    } else {
      let pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
      let re = new RegExp("[^\u4e00-\u9fa5]");
      if (pattern.test(value)) {
        callback([new Error('请输入正确的系统编码！')]);
      }else if(!re.test(value)){
        callback([new Error('请输入英文或数字！')]);
      }else {
        callback();
      }
    }
  },
  /*表单校验规则 END*/

  render() {
    /*表单初始化*/
    const { getFieldProps } = this.props.form;
    return (
      <div>
        <Form onSubmit={this.addOk} className="login-form">
          <FormItem label="名称" hasFeedback >
            <Input type="text" placeholder="请输入系统名称"
              {...getFieldProps('addsystemName',{rules:[{ required: true, message: '请填写正确的系统名称！'},{validator: this.systemExists}]})}
            />
          </FormItem>
          <FormItem label="编码" hasFeedback>
            <Input type="text" placeholder="请输入系统编码"
              {...getFieldProps('addsystemNum',{rules:[{ required: true, message: '请填写正确的系统编码！'},{validator: this.systemNum}]})}
            />
          </FormItem>
          <FormItem label="状态" >
            <RadioGroup {...getFieldProps('addstatus', { initialValue: '1' })}>
              <Radio value="1">激活</Radio>
              <Radio value="0">失效</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="描述" >
            <Input type="textarea" {...getFieldProps('addremark', { initialValue: '' })}/>
          </FormItem>
        </Form>
        <Button key="submit" type="primary" size="large" onClick={this.addOk}>确定</Button>
      </div>
    );
  }
});

systemCreat = createForm()(systemCreat);
export default systemCreat;
