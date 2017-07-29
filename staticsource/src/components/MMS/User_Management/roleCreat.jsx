import React, { Component, PropTypes } from 'react';
import { Radio, Button, Form, Input, message, Select, Col} from 'antd';
import { Router, Route, IndexRoute, Link } from 'react-router';
import {  getMenuSystem, getParentMenuName,  getPageResources, addRoleMsg } from '../../../services/api.js';

const createForm = Form.create;
const Option = Select.Option;
const FormItem = Form.Item;
const RadioGroup = Radio.Group;

let uuid = 0;

let roleCreat = React.createClass({
  //	页面初始化
  getInitialState(){
    return {
      menuSystem:[],
      parentMenu:[],
      pageData:[],
      parentSystemVal:"",
      parentMenuVal:""
    }
  },

  /*初始化表单*/
  componentDidMount() {
    this.props.form.resetFields();
    /*获取系统列表*/
    let val = {"stateCd":"1"};
    getMenuSystem(val).then((msg)=>{
      this.setState({ menuSystem:msg.jsonResult.data.list });
    });
  },
  /*新建系统资源*/

  /*根据所选系统，获取菜单列表*/
  handleSelectChange(value){
    this.setState({ parentSystemVal:value });
    let val = {
      "systemPlatformCd":value,
      "stateCd":"1"
    };
    getParentMenuName(val).then((msg)=>{
      this.setState({ parentMenu:msg.jsonResult.data.list });
    });
  },
  /*根据所选菜单，获取资源列表*/
  selectParentMenu(value){
    if (value == "" || value==undefined){
      return false
    }else{
      this.setState({ parentMenuVal:value.join(',') });
      let val = {
        "stateCd":"1",
        "menuIds":value.join(',')
      };
      getPageResources(val).then((msg)=>{
        this.setState({ pageData:msg.jsonResult.data });
      });
    }
  },
  addOk(e) {
    e.preventDefault();
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        return;
      }
      let resMap = [];
      let allowMenu ;
      for (let y = 0 ; y < values.keys.length; y++){
        let allowPage = values[`allowPage`+y]+values[`allowPageType`+y];
        if (!values[`allowPage`+y]){
          resMap[y] = null;
        }else{
          resMap[y] = allowPage;
        }
      }
      //判断可访问菜单是否为空，为空是传null
      if (!values.allowMenu){
        allowMenu = null;
      }else{
        allowMenu = values.allowMenu.join(",")
      }

      let addRoleData={
        "systemPlatformCd":this.state.parentSystemVal,
        "roleName":values.roleName,
        "stateCd":values.roleStatus,
        "roleDesc":values.roleRemark,
        "menuIds":allowMenu,
        "resMap": resMap
      };
      addRoleMsg(addRoleData).then((msg)=>{
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
  /*表单校验规则 END*/

  /*动态增加删除可访问资源 控制*/
  remove(k) {
    const { form } = this.props;
    let keys = form.getFieldValue('keys');
    keys = keys.filter((key) => {
      return key !== k;
    });
    form.setFieldsValue({ keys });
  },
  add() {
    uuid++;
    const { form } = this.props;
    let keys = form.getFieldValue('keys');
    keys = keys.concat(uuid);
    form.setFieldsValue({ keys });
  },
  /*动态增加删除可访问资源 控制 END*/

  render() {
    const { getFieldProps,getFieldValue } = this.props.form;

    //渲染系统列表
    const menuSystemList = this.state.menuSystem.map((items,index)=>{
      return (
        <Option key={index} value={items.systemPlatformCd}>{items.systemPlatformName}</Option>
      )
    });
    //渲染菜单列表
    const ParentMenuList = this.state.parentMenu.map((items,index)=>{
      return (
        <Option key={index} value={items.menuId}>{items.menuName}</Option>
      )
    });
    //渲染资源列表
    const pageResourcesList = this.state.pageData.map((items,index)=>{
      return (
        <Option key={index} value={items.privilegeResId}>{items.resName}</Option>
      )
    });

    /*资源操作类型*/
    getFieldProps('keys', {
      initialValue: [0]
    });

    const formItems = getFieldValue('keys').map((k) => {
      return (
        <Form.Item key={k} style={{marginBottom:"20px"}}>
          <Col span="9">
          <FormItem hasFeedback>
            <Select size="large"
              {...getFieldProps(`allowPage${k}`,{rules:[{ required: false, message: '请选择可访问资源！'}]})}
            >
              {pageResourcesList}
            </Select>
          </FormItem>
          </Col>
          <Col span="1"></Col>
          <Col span="9">
            <FormItem hasFeedback>
              <Select size="large"
                {...getFieldProps(`allowPageType${k}`,{initialValue:"1",rules:[{ required: false, message: '请选择操作类型！'}]})}
              >
                <Option value="1">可见</Option>
                <Option value="2">不可见</Option>
                <Option value="3">可编辑</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span="1"></Col>
          <Col span="4">
            <Button size="large" onClick={() => this.remove(k)}>删除</Button>
          </Col>
        </Form.Item>
      );
    });
    /*资源操作类型*/
    return (
      <div>
        <Form onSubmit={this.addOk} className="login-form">
          <FormItem label="角色名称" hasFeedback>
            <Input type="text" placeholder="请输入角色名称（必填）"
              {...getFieldProps('roleName',{rules:[{ required: true, message: '请填写正确的角色名称！'},{validator: this.userExists}]})}
            />
          </FormItem>
          <FormItem label="可访问系统">
            <Select size="large" defaultValue="请选择"
              {...getFieldProps('allowSystem',{onChange:this.handleSelectChange,rules:[{ required: true, message: '请选择可访问系统！'}]})}
            >
              {menuSystemList}
            </Select>
          </FormItem>
          <FormItem label="可访问菜单">
            <Select size="large" multiple
              {...getFieldProps('allowMenu',{onChange:this.selectParentMenu,rules:[{ required: false, message: '请选择可访问菜单！',type:'array'}]})}
            >
              {ParentMenuList}
            </Select>
          </FormItem>
          <FormItem label="可访问资源">
            {formItems}
            <Form.Item wrapperCol={{ span: 10, offset: 0 }}>
              <Button onClick={this.add}>新增</Button>
            </Form.Item>
          </FormItem>
          <FormItem label="是否激活" >
            <RadioGroup {...getFieldProps('roleStatus', { initialValue: '1' })}>
              <Radio value="1">激活</Radio>
              <Radio value="0">失效</Radio>
            </RadioGroup>
          </FormItem>
          <FormItem label="描述" >
            <Input type="textarea" {...getFieldProps('roleRemark', { initialValue: '' })}/>
          </FormItem>
        </Form>
        <Button key="submit" type="primary" size="large" onClick={this.addOk}>确定</Button>
      </div>
    );
  }
});
roleCreat = createForm()(roleCreat);
export default roleCreat;
