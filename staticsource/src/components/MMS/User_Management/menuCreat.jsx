import React, { Component, PropTypes } from 'react';
import {  Button,  Form,  Input,   message, Radio,  Select, Col } from 'antd';
import { Router, Route, IndexRoute, Link } from 'react-router';
import { addMenu, getMenuSystem, getShowOlder, getParentMenuName, getShowUsers } from '../../../services/api.js'

const createForm = Form.create;
const Option = Select.Option;
const FormItem = Form.Item;
const RadioGroup = Radio.Group;


let menuCreat = React.createClass({

  //	页面初始化
  getInitialState(){
    this.props.form.resetFields();
    return {
      userMsg: [],
      menuSystem:[],
      parentMenu:[],
      userShowdata:[],
      parentSystemVal:"",
      showOlder:[],
      parentShowOder:[]
    }
  },

  /*初始化表单*/
  componentDidMount() {
    /*获取系统列表*/
    let val = {
      "stateCd":"1"
    };
    getMenuSystem(val).then((msg)=>{
      this.setState({
        menuSystem:msg.jsonResult.data.list
      });
    });
  },


  /*新建系统资源*/
  handleSelectChange(value){
    this.setState({
      parentSystemVal:value
    });
    /*根据所选系统，获取菜单列表*/
    let val = {
      "systemPlatformCd":value,
      "stateCd":"1"
    };
    getParentMenuName(val).then((msg)=>{
      this.setState({
        parentMenu:msg.jsonResult.data.list
      });
    });
    let showUserMsg = {
      "stateCd":"1",
      "systemPlatformCd":value
    };
    getShowUsers(showUserMsg).then((msg)=>{
      this.setState({
        userShowdata:msg.jsonResult.data.list
      });
    })
  },
  parentMenuSelect(e){
    getShowOlder(e).then((msg)=>{
       this.setState({
        parentShowOder:msg.jsonResult.data.showOrder
      });
    })
  },
  addOk(e) {
    e.preventDefault();
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        return;
      }
      let systemMsg={
        "menuName":values.addMenuName,
        "url":values.MenuUrl,
        "systemPlatformCd":values.menuParentSystem,
        "parentId":values.menuParentMenu,
        "showOrder":values.ParentShowOrder+values.showOrder,
        "stateCd":values.menuStatus,
        "menuDesc":values.menuRemark
      };
      addMenu(systemMsg).then((msg)=>{
        if (!msg.jsonResult.success){
          message.error(msg.jsonResult.resultMsg);
        }else{
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
  showOderExists(rule, value, callback){
    if (!value) {
      callback();
    } else {
      if (!isFinite(value)) {
        callback([new Error('请输入数字！')]);
      }else if (value.length > 2){
        callback([new Error('请输入0-99之间的数字！')]);
      }else if (value.length <2){
        callback([new Error('展示顺序必须为两位数！')]);
      }else {
        callback();
      }
    }
  },
  menuOderExists(rule, value, callback){
    if (!value) {
      callback();
    } else {
      let patrn = /^[1-9]$/ig;
      if (!patrn.test(value)) {
        callback([new Error('请输入1-10之间的数字！')]);
      }else if (!isFinite(value)){
        callback([new Error('请输入数字！')]);
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
    return (
      <div>
        <Form onSubmit={this.addOk} className="login-form">
          <FormItem label="菜单名称" hasFeedback>
            <Input type="text" placeholder="请输入菜单名称（必填）"
              {...getFieldProps('addMenuName',{rules:[{ required: true, message: '请填写菜单名称！'},{validator: this.userExists}]})}
            />
          </FormItem>
          <FormItem label="入口URL">
            <Input type="text" placeholder="请输入入口URL（以‘/’开头）"
              {...getFieldProps('MenuUrl',{initialValue: "/",rules:[{ required: true, message: '请填写正确的URL！'},{validator: this.urlExists}]})}
            />
          </FormItem>
          <FormItem label="所属系统">
            <Select size="large" defaultValue="请选择"
              {...getFieldProps('menuParentSystem',{onChange:this.handleSelectChange,rules:[{ required: true, message: '请填写所属系统！'}]})}
            >
              {menuSystemList}
            </Select>
          </FormItem>
          <FormItem label="上级菜单">
            <Select size="large"
              {...getFieldProps('menuParentMenu',{onChange:this.parentMenuSelect,initialValue: null})}
            >
              <Option value={null}>不选择</Option>
              {ParentMenuList}
            </Select>
          </FormItem>
          <div style={{width:"auto",height:"auto",overflow:"hidden"}}>
            <Col span="11">
              <FormItem label="上级菜单顺序">
                <Input type="text" disabled
                  {...getFieldProps('ParentShowOrder',{initialValue: this.state.parentShowOder})}
                />
              </FormItem>
            </Col>
            <Col span="2"></Col>
            <Col span="11">
              <FormItem label="本级菜单顺序">
                <Input type="text" placeholder="请输入显示顺序（选填）"
                  {...getFieldProps('showOrder',{rules:[{ required: true, message: '请输入显示顺序！'},{validator: this.showOderExists}]})}
                />
              </FormItem>
            </Col>
          </div>
          <FormItem label="是否激活" >
            <RadioGroup {...getFieldProps('menuStatus', { initialValue: '1' })}>
              <Radio value="1">激活</Radio>
              <Radio value="0">失效</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="描述" >
            <Input type="textarea" {...getFieldProps('menuRemark', { initialValue: '' })}/>
          </FormItem>
        </Form>
        <Button key="submit" type="primary" size="large" onClick={this.addOk}>确定</Button>
      </div>
    );
  }
});

menuCreat = createForm()(menuCreat);
export default menuCreat;
