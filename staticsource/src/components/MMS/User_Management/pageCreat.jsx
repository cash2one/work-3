import React, { Component, PropTypes } from 'react';
import {  Button, Form, Input, message, Radio, Select } from 'antd';
import { Router, Route, IndexRoute, Link } from 'react-router';
import { addPage, getMenuSystem, getParentMenuName, getPageType } from '../../../services/api.js'

const createForm = Form.create;
const Option = Select.Option;
const FormItem = Form.Item;
const RadioGroup = Radio.Group;

let pageCreat = React.createClass({

  //	页面初始化
  getInitialState(){
    this.props.form.resetFields();
    return {
      menuSystem:[],
      parentMenu:[],
      pageTypeList:[],
      typeMark:false
    }
  },

  /*初始化表单*/
  componentDidMount() {
    let val = {
      "stateCd":"1"
    };

    getMenuSystem(val).then((msg)=>{
      this.setState({ menuSystem:msg.jsonResult.data.list });
    });
    getPageType().then((msg)=>{
      this.setState({ pageTypeList:msg.jsonResult.data });
    })
  },

  /*新建系统资源*/
  /*根据所选系统，获取菜单列表*/
  systemSelectChange(value){
    let val = {
      "systemPlatformCd":value,
      "stateCd":"1"
    };
    getParentMenuName(val).then((msg)=>{
      this.setState({ parentMenu:msg.jsonResult.data.list });
    });
  },
  pageType(value){
    if (value == "da8fe8e2fae444848142f137bf7ff675"){
      this.setState({typeMark:false})
    }else {
      this.setState({typeMark:true})
    }
  },

  addOk(e) {
    e.preventDefault();
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        return;
      }
      let pageResTypeName = values.pageResTypeName;
      let mark = values.pageResTypeMark;
      if (pageResTypeName != "da8fe8e2fae444848142f137bf7ff675"){
        mark = null
      }
      let systemMsg={
        "resName":values.pageResName,
        "url":values.pageUrl,
        "stateCd":values.pageStatus,
        "menuId":values.pageParentMenu,
        "systemPlatformCd":values.pageParentSystem,
        "resTypeCd":pageResTypeName,
        "resDesc":values.pageDesc,
        "mark":mark
      };
      addPage(systemMsg).then((msg)=>{
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
  userExists(rule, value, callback) {
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

  urlExists(rule, value, callback) {
    if (!value) {
      callback();
    } else {
      let pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
      let re = new RegExp("[^\u4e00-\u9fa5]");
      if (!pattern.test(value)) {
        callback([new Error('请输入正确的特殊字符！')]);
      }else if (!re.test(value)){
        callback([new Error('请输入正确的url地址！')]);
      }else {
        callback();
      }
    }
  },
  /*表单校验规则 END*/

  render() {
    /*表单初始化*/
    const { getFieldProps } = this.props.form;

    /*新建菜单 获取系统*/
    const menuSystemList = this.state.menuSystem.map((items,index)=>{
      return (
        <Option key={index} value={items.systemPlatformCd}>{items.systemPlatformName}</Option>
      )
    });
    /*新建菜单 获取系统 END*/

    /*根据系统 获取菜单*/
    const ParentMenuList = this.state.parentMenu.map((items,index)=>{
      return (
        <Option key={index} value={items.menuId}>{items.menuName}</Option>
      )
    });
    /*根据系统 获取菜单 END*/


    //新建时，获取资源类型渲染
    const pageTypeDate = this.state.pageTypeList.map((items,index)=>{
      return (
        <Option key={index} value={items.resTypeCd}>{items.resTypeName}</Option>
      )
    });

    return (
      <div>
        <Form onSubmit={this.addOk} className="login-form">
          <FormItem label="页面资源名称" hasFeedback>
            <Input type="text" placeholder="请输入菜单名称（必填）"
              {...getFieldProps('pageResName',{rules:[{ required: true, message: '请填写正确的菜单名称！'},{validator: this.userExists}]})}
            />
          </FormItem>
          <FormItem label="入口URL">
            <Input type="text" placeholder="请输入入口URL（选填）"
              {...getFieldProps('pageUrl',{initialValue:"/",rules:[{ required: true, message: '请填写正确的URL！'},{validator: this.urlExists}]})}
            />
          </FormItem>
          <FormItem label="所属系统">
            <Select id="select" size="large" defaultValue="请选择"
              {...getFieldProps('pageParentSystem',{onChange:this.systemSelectChange,rules:[{ required: true, message: '请填写所属系统！'}]})}
            >
              {menuSystemList}
            </Select>
          </FormItem>
          <FormItem label="所属菜单">
            <Select id="select" size="large" defaultValue="请选择"
              {...getFieldProps('pageParentMenu',{rules:[{ required: true, message: '请填写所属菜单！'}]})}>
              {ParentMenuList}
            </Select>
          </FormItem>
          <FormItem label="资源类型">
            <Select size="large" defaultValue="请选择" {...getFieldProps('pageResTypeName',{onChange:this.pageType,initialValue:'da8fe8e2fae444848142f137bf7ff675' ,rules:[{ required: true, message: '请填写资源类型！'}] })}>
              {pageTypeDate}
            </Select>
          </FormItem>
          <FormItem label="资源标示">
            <Select size="large" disabled={this.state.typeMark} {...getFieldProps('pageResTypeMark',{ initialValue:'add'})}>
              <Option value="add">增加</Option>
              <Option value="del">删除</Option>
              <Option value="update">修改</Option>
            </Select>
          </FormItem>
          <FormItem label="是否激活" >
            <RadioGroup {...getFieldProps('pageStatus', { initialValue: '1' })}>
              <Radio value="1">激活</Radio>
              <Radio value="0">失效</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="描述" >
            <Input type="textarea" {...getFieldProps('pageDesc', { initialValue: '' })}/>
          </FormItem>
        </Form>
        <Button key="submit" type="primary" size="large" onClick={this.addOk}>确定</Button>
      </div>
    );
  }
});

pageCreat = createForm()(pageCreat);
export default pageCreat;
